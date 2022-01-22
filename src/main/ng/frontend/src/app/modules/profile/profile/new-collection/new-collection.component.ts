import {Component,OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../../service/auth/auth.service";
import {CollectionsService} from "../../../../service/collections/collections.service";
import {ActivatedRoute, Router} from "@angular/router";
import {CollectionModel} from "../../../../model/collections";
import {ImageService} from "../../../../service/img/image.service";
import {AuthuserModel} from "../../../../model/authuser";
import {UserService} from "../../../../service/user/user.service";
import {ImageModel} from "../../../../model/Image";
import {NgxSpinnerService} from "ngx-spinner";


interface Topic {
    val: string;
    viewVal: string;
}


@Component({
    selector: 'app-new-collection',
    templateUrl: './new-collection.component.html',
    styleUrls: ['./new-collection.component.scss']
})
export class NewCollectionComponent implements OnInit {

    formControl: FormGroup;
    currentId: any;
    newColl: CollectionModel = new CollectionModel();
    resCol: CollectionModel = new CollectionModel();
    createmes = false;
    editColId:number
    errMess: any;
    image: File;
    imageMin: File;
    id: number;
    owner: AuthuserModel;
    status = false;
    count = false;
    edit=false;
    img:ImageModel;
    files: any[] = [];
    fC = new FormControl;
    optionalFields=['Number#1','Number#2','Number#3','String#1','String#2','String#3',
        'Text#1','Text#2','Text#3','Data#1','Data#2', 'Data#3','Checkbox#1','Checkbox#2','Checkbox#3'];
    selectedFields=[];
    i;

    constructor(private auth: AuthService, private col: CollectionsService, private activeRout: ActivatedRoute,
                private rout: Router, private fB: FormBuilder, private imageService: ImageService, private user: UserService,private spinner: NgxSpinnerService) {
    }

    ngOnInit(): void {
        this.spinner.hide();
        this.initReactForm();
        if (this.auth.user == null) {
            this.rout.navigate(['/'])
        } else if (this.activeRout.snapshot.params['editId']!=null || NaN){
            this.auth.check();
            this.editColId= +this.activeRout.snapshot.params['editId'];
            this.getColections(this.editColId);
            this.edit=true;
        } else if (this.auth.user.role.toString() == "ADMIN") {
            this.auth.check();
            this.status = true;
            this.edit=false;
            this.id = +this.activeRout.snapshot.params['id'];
            this.getOwner(this.id);
            this.currentId = this.auth.user.id;
        } else if (this.auth.user.role.toString() != "USER") {
            this.auth.check();
            this.edit=false;
            this.currentId = this.auth.user.id;
        }
    }

    private getOwner(id: number): any {
        this.user.getUser(id).subscribe(data =>
            this.owner = data)
    }

    private initReactForm() {
        this.formControl = this.fB.group({
            name: ['', Validators.required],
            description: ['', Validators.required],
            topic: ['', Validators.required],
            number1: [''],
            number2: [''],
            number3: [''],
            string1:[''],
            string2:[''],
            string3:[''],
            text1:[''],
            text2:[''],
            text3:[''],
            data1:[''],
            data2:[''],
            data3:[''],
            checkbox1:[''],
            checkbox2:[''],
            checkbox3:['']
        })
    }


    topics: Topic[] = [
        {val: 'Alcohol', viewVal: 'Alcohol'},
        {val: 'Books', viewVal: 'Books'},
        {val: 'Coins', viewVal: 'Coins'},
        {val: 'Marks', viewVal: 'Marks'},
        {val: 'Pin', viewVal: 'Pin'}
    ]

    create(): any {
        this.spinner.show();
        this.status == false ? this.newColl.owner = this.auth.user : this.newColl.owner = this.owner;
        if (this.files.length != 0) {
            this.files.forEach(s => {
                this.imageService.upload(s).subscribe(data => {
                        console.log("file uploaded")
                        this.newColl.img= data as ImageModel;
                        this.createCollection(this.newColl);
                    },
                    error => console.log(error)
                )
            })
        }else {
            this.createCollection(this.newColl);
        }

    }

    editCol():any{
        this.spinner.show();
        if (this.files.length != 0) {
            this.files.forEach(s => {
                this.imageService.upload(s).subscribe(data => {
                        console.log("file uploaded")
                        this.newColl.img= data as ImageModel;
                        this.sendEdit(this.newColl);
                    },
                    error => console.log(error))})
        }else {
            this.sendEdit(this.newColl);
        }

    }

    private sendEdit(col: CollectionModel):any{
        this.col.editCollection(col).subscribe(data => {
            this.rout.navigate(['/profile/collect/'+this.newColl.id])
        },
            error=>console.log(error))
    }

    private getColections(editColId: number) {
        this.col.findCollectById(editColId).subscribe(s=>{
                this.newColl=s as CollectionModel;
                this.checkColumns(this.newColl);
            },
            error=>console.log(error))

    }

    private createCollection(newColl: CollectionModel) {
        this.col.saveNewCol(newColl).subscribe(data => {
                this.createmes = true,
                    this.spinner.hide;
                this.status == false ? this.rout.navigate(['/profile']) : this.rout.navigate(['/profile/' + this.id])
            },
            error => console.log(error));
    }

    onUpload() {
        this.imageService.upload(this.image).subscribe(data => {
                this.rout.navigate(['/'])
            },
            error => {
                console.log(error);
                // this.reset();
            })
    }

    onFileChange(event) {
        this.image = event.target.file[0];
        const fileReader = new FileReader();
        fileReader.onload = (even: any) => {
            this.imageMin = even.target.result;
        };
        fileReader.readAsDataURL(this.image);
    }

    onFileDropped($event) {
        this.prepareFilesList($event);
    }

    fileBrowseHandler(files) {
        this.prepareFilesList(files);
    }

    deleteFile(index: number) {
        this.count = false
        this.files.splice(index, 1);
    }

    prepareFilesList(files: Array<any>) {
        files.length == 1 ? this.count = true : this.count = false;
        for (const item of files) {
            this.files.push(item);
        }
    }

    formatBytes(bytes, decimals) {
        if (bytes === 0) {
            return '0 Bytes';
        }
        const k = 1024;
        const dm = decimals <= 0 ? 0 : decimals || 2;
        const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];
        const i = Math.floor(Math.log(bytes) / Math.log(k));
        return parseFloat((bytes / Math.pow(k, i)).toFixed(dm)) + ' ' + sizes[i];
    }


    private checkColumns(newColl: CollectionModel) {
        this.newColl.numberName1!=null ? this.selectedFields.push("Number#1") : false;
        this.newColl.numberName2!=null ? this.selectedFields.push("Number#2") : false;
        this.newColl.numberName3!=null ? this.selectedFields.push("Number#3") : false;
        this.newColl.stringName1!=null ? this.selectedFields.push("String#1") : false;
        this.newColl.stringName2!=null ? this.selectedFields.push("String#2") : false;
        this.newColl.stringName3!=null ? this.selectedFields.push("String#3") : false;
        this.newColl.textName1!=null ? this.selectedFields.push("Text#1") : false;
        this.newColl.textName2!=null ? this.selectedFields.push("Text#2") : false;
        this.newColl.textName3!=null ? this.selectedFields.push("Text#3") : false;
        this.newColl.dataName1!=null ? this.selectedFields.push("Data#1") : false;
        this.newColl.dataName2!=null ? this.selectedFields.push("Data#2") : false;
        this.newColl.dataName3!=null ? this.selectedFields.push("Data#3") : false;
        this.newColl.checkboxName1!=null ? this.selectedFields.push("Checkbox#1") : false;
        this.newColl.checkboxName2!=null ? this.selectedFields.push("Checkbox#2") : false;
        this.newColl.checkboxName3!=null ? this.selectedFields.push("Checkbox#3") : false;

    }
}
