import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../../service/auth/auth.service";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {CollectionsService} from "../../../service/collections/collections.service";
import {CollectionModel} from "../../../model/collections";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  currentId: any;
  currentUser: any;
  currentEmail:any;
  currentRole:any;
  collections:CollectionModel[];
  check=false;
  delMess=false;
  id:number;
  idConv:any;

  constructor(private auth:AuthService,private http: HttpClient,
              private active: ActivatedRoute,private router:Router,
              private collect: CollectionsService) { }

  ngOnInit(): void {
    if (this.auth.user==null){
      this.router.navigate(['/']);
    }else{
      this.auth.check();
    }
    if (this.auth.user.role.toString()=="ADMIN"){
      this.id = +this.active.snapshot.params['id'];
      isNaN(this.id)? this.idConv=this.auth.user.id : this.idConv= +this.active.snapshot.params['id'];
      this.getMyCollect(this.idConv);
    }else{
      this.currentUser=this.auth.user.name;
      this.currentEmail=this.auth.user.email;
      this.idConv=this.auth.user.id;
      this.getMyCollect(this.idConv);
    }
  }

  createNewCollection(){
    this.auth.user.role.toString()!="ADMIN" ? this.router.navigate(['profile/collect']) :
        this.router.navigate(['profile/col/'+this.id])
  }

  deleteCollection(id: number):void {
    if(confirm('Are you sure you want to delete with all items?')){
      this.collect.delete(id).subscribe( () => {
            this.delMess=true;
            this.getMyCollect(this.idConv);
          },
          error => console.log(error));

    }
  }

  private getMyCollect(currentId: number): void{
    this.collect.findAllByUser(currentId).subscribe(collection=>{
      this.collections = collection as CollectionModel[];
      if (this.collections.length!=0){
        this.check=true;
      }
    })
  }

  goCheck(id) {
    this.router.navigate(['profile/collect/'+id]);
  }

  editCollection(editId) {
    this.router.navigate(['profile/collect/edit/'+editId]);
  }
}
