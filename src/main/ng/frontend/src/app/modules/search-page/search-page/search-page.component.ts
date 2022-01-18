import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ItemsService} from "../../../service/collections/items.service";
import {ItemModel} from "../../../model/item";

@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrls: ['./search-page.component.css']
})
export class SearchPageComponent implements OnInit {

  id:number;
  url:string;
  items:ItemModel[]=[];
  constructor(private rout:Router,private route:ActivatedRoute,private itemService:ItemsService) { }

  ngOnInit(): void {
    if (window.location.href.indexOf('tag')){
      this.id = +this.route.snapshot.params['id'];
      this.findItemsByTagId(this.id)
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
}
