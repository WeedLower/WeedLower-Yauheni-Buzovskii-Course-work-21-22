import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
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

interface Field {
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
    errMess: any;
    image: File;
    imageMin: File;
    id: number;
    owner: AuthuserModel;
    status = false;
    count = false;
    img:ImageModel;

    files: any[] = [];

    constructor(private auth: AuthService, private col: CollectionsService, private activeRout: ActivatedRoute,
                private rout: Router, private fB: FormBuilder, private imageService: ImageService, private user: UserService,private spinner: NgxSpinnerService) {
    }

    ngOnInit(): void {
        this.auth.check();
        this.initReactForm();
        if (this.auth.user == null) {
            this.rout.navigate(['/'])
        } else if (this.auth.user.role.toString() == "ADMIN") {
            this.status = true;
            this.id = +this.activeRout.snapshot.params['id'];
            this.getOwner(this.id);
            this.currentId = this.auth.user.id;
        } else if (this.auth.user.role.toString() != "USER") {
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
            topic: ['', Validators.required]
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
                        console.log(data)
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

    private createCollection(newColl: CollectionModel) {
        this.col.saveNewCol(newColl).subscribe(data => {
                console.log(data);
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


}
