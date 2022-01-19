import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {CollectionsService} from "../../../service/collections/collections.service";
import {CollectionModel} from "../../../model/collections";
import {ItemModel} from "../../../model/item";
import {ItemsService} from "../../../service/collections/items.service";
import {Router} from "@angular/router";
import {TagModel} from "../../../model/tag";
import {TagService} from "../../../service/tag/tag.service";
import { AuthService } from 'src/app/service/auth/auth.service';


@Component({
    selector: 'app-main-page',
    templateUrl: './main-page.component.html',
    styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit{
    collections:CollectionModel[];
    items:ItemModel[]=[];
    i:ItemModel[]=[];
    tags:TagModel[];
    tagModel:TagModel;
    constructor(private collect: CollectionsService,
                private itm: ItemsService,private router:Router,
                private tagService:TagService,private auth:AuthService) { }

    ngOnInit(): void {
        this.getAllTags();
        this.getBiggestCollection();
        this.getLastAddItems();
        this.auth.check();
    }

    private getBiggestCollection():void{
        this.collect.getAll().subscribe(collection =>{
            this.collections = collection as CollectionModel[];
        })
    }

    private getLastAddItems():void {
        this.itm.getAll().subscribe(data=>{
            this.items=data as ItemModel[];
            data.forEach(s=>this.i.unshift(s))
            this.i.length=5;
        })
    }

    viewItem(id) {
        this.router.navigate(['/item/'+id])
    }

    private getAllTags() {
        this.tagService.getAll().subscribe(data => {
                console.log(data);
                this.tags=data as TagModel[];
            },
            error=>console.log(error))
    }

    searchByTag(id){
        this.router.navigate(['/search/tag/'+id])
    }
}
