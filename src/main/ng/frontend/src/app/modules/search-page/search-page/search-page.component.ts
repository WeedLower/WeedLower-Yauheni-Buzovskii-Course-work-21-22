import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, NavigationEnd, Router} from "@angular/router";
import {ItemsService} from "../../../service/collections/items.service";
import {ItemModel} from "../../../model/item";
import {SearchService} from "../../../service/search/search.service";
import {filter} from "rxjs";

@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrls: ['./search-page.component.css']
})
export class SearchPageComponent implements OnInit {

  router:any='';
  id:number;
  searchString:string;
  items:ItemModel[]=[];

  constructor(private rout:Router,private route:ActivatedRoute,private itemService:ItemsService,private searchService:SearchService) {
    this.router=this.rout.url;
  }

  ngOnInit(): void {
    this.rout.events.pipe(
        filter(event => event instanceof NavigationEnd)
    ).subscribe(()=>{
      this.searchString = this.route.snapshot.params['searchString']
      this.search()
    })
    if(this.route.snapshot.params['id']!=null){
      this.id = +this.route.snapshot.params['id'];
      this.findItemsByTagId(this.id)
    }else if(this.route.snapshot.params['searchString']!=null){
      this.searchString = this.route.snapshot.params['searchString']
      this.search()
    }
  }

  private findItemsByTagId(id: number) {
    this.itemService.findItemsByTagId(id).subscribe(data=>{
      console.log(data);
      this.items=data as ItemModel[];
    });
  }

  viewItem(id) {
    this.rout.navigate(['/item/'+id])
  }

  private search() {
    this.searchService.search(this.searchString).subscribe(data=>{
          this.items=data as ItemModel[];
        },
        error=> console.log(error))
  }
}
