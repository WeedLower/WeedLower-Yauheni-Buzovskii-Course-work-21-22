import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../../service/auth/auth.service";
import {CollectionsService} from "../../../../service/collections/collections.service";
import {ActivatedRoute, Router} from "@angular/router";
import {CollectionModel} from "../../../../model/collections";
import {error} from "protractor";
import {ImageService} from "../../../../service/img/image.service";
import {AuthuserModel} from "../../../../model/authuser";
import {UserService} from "../../../../service/user/user.service";

interface Topic{
  val:string;
  viewVal:string;
}
interface Field{
  val:string;
  viewVal:string;
}
@Component({
  selector: 'app-new-collection',
  templateUrl: './new-collection.component.html',
  styleUrls: ['./new-collection.component.scss']
})
export class NewCollectionComponent implements OnInit {

  @ViewChild('imageInputFile',{static:false}) imageFile:ElementRef;
  formControl:FormGroup;
  currentId: any;
  newColl:CollectionModel = new CollectionModel();
  resCol: CollectionModel = new CollectionModel();
  createmes=false;
  errMess:any;
  image:File;
  imageMin:File;
  id:number;
  owner: AuthuserModel;
status=false;
  constructor(private auth:AuthService,private col:CollectionsService,private activeRout:ActivatedRoute,
              private rout:Router,private fB:FormBuilder,private imageService:ImageService,private user:UserService) {

  }

  ngOnInit(): void {
    this.initReactForm();
    if (this.auth.user==null){
      this.rout.navigate(['/'])
    }else if (this.auth.user.role.toString()=="ADMIN"){
      this.status=true;
      this.id = +this.activeRout.snapshot.params['id'];
      this.getOwner(this.id);
      this.currentId= this.auth.user.id;
    }else if (this.auth.user.role.toString()!="USER"){
      this.currentId= this.auth.user.id;

    }
  }

  private getOwner(id: number):any {
    this.user.getUser(id).subscribe(data=>
    this.owner = data)
  }

  private initReactForm() {
    this.formControl = this.fB.group({
      name:['',Validators.required],
      description:['',Validators.required],
      topic:[''],
    })
  }
  topics: Topic[] =[
    {val:'Alcohol',viewVal:'Alcohol'},
    {val:'Books',viewVal:'Books'},
    {val:'Coins',viewVal:'Coins'},
    {val:'Marks',viewVal:'Marks'},
    {val:'Pin',viewVal:'Pin'}
  ]
  create(): any {
    this.status==false ? this.newColl.owner=this.auth.user : this.newColl.owner=this.owner;
      this.col.saveNewCol(this.newColl).subscribe(data=>{
        console.log(data);
        this.createmes=true
        this.status==false ? this.rout.navigate(['/profile']) : this.rout.navigate(['/profile/'+this.id])
      },
          error=>console.log(error));
  }

  onUpload() {
  }

  onFileChange(event) {
    this.image = event.target.file[0];
    const fileReader = new FileReader();
    fileReader.onload=(even: any) =>{
      this.imageMin = even.target.result;
    };
    fileReader.readAsDataURL(this.image);
  }

}
