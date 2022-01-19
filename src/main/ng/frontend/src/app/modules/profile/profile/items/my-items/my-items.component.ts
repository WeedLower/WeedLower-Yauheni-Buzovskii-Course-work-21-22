import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {CollectionsService} from "../../../../../service/collections/collections.service";
import {CollectionModel} from "../../../../../model/collections";
import {ItemsService} from "../../../../../service/collections/items.service";
import {ItemModel} from "../../../../../model/item";
import {MatSort, Sort} from "@angular/material/sort";
import {LiveAnnouncer} from "@angular/cdk/a11y";
import {SelectionModel} from "@angular/cdk/collections";
import {MatTableDataSource} from "@angular/material/table";
import {empty} from "rxjs";
import {error} from "protractor";
import {AuthService} from "../../../../../service/auth/auth.service";


@Component({
  selector: 'app-my-items',
  templateUrl: './my-items.component.html',
  styleUrls: ['./my-items.component.css']
})
export class MyItemsComponent implements OnInit {
  collection: CollectionModel;
  sortedData: ItemModel[];
  newItem: ItemModel = new ItemModel();
  id: number;
  status=true;
  displayedColumns: string[] = ['select', 'id', 'name','tags','view'];
  selection = new SelectionModel<ItemModel>(true, []);
  item = new MatTableDataSource<ItemModel>()



  constructor(private route: ActivatedRoute, private collectionsService: CollectionsService,
              private itemsService: ItemsService,private auth:AuthService,
              private rout: Router, private _liveAnnouncer: LiveAnnouncer) {
  }

  @ViewChild(MatSort) sort: MatSort;

  ngOnInit(): void {
    this.auth.check();
    if (this.auth.user.role.toString()=='ADMIN'){

    }
    this.id = +this.route.snapshot.params['id'];
    if (this.id != null) {
      this.getCollect(this.id)
      this.getCollectItems(this.id)
    }
  }


  ngAfterViewInit() {
    this.item.sort = this.sort;
  }

  private getCollect(id): void {
    this.collectionsService.findCollectById(id).subscribe((data) =>{
      data.user.id!=this.auth.user.id && this.auth.user.role.toString()!="ADMIN" ? this.rout.navigate(['/']) :
      this.collection = data as CollectionModel;
    },
        error=>console.log(error))
  };

  private getCollectItems(id: number): void {
    this.itemsService.getAllItemsByCollection(id).subscribe((items) => {
          this.item.data = items as ItemModel[];
        }
    )

  }

  delete():void{
    if (confirm('Delete item / items?')){
      this.selection.selected.forEach(id =>
          this.itemsService.delete(id.id).subscribe(data=>{
            console.log(data);
            this.getCollectItems(this.id);
          },
              error=> console.log(error)));
    }
  }

  openDialog() {
    this.rout.navigate(['profile/collect/' + this.id + '/new-item'])
  }

  announceSortChange(sortState: Sort) {
    if (sortState.direction) {
      this._liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
    } else {
      this._liveAnnouncer.announce('Sorting cleared');
    }
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.item.data.length;
    return numSelected === numRows;
  }

  masterToggle() {
    this.isAllSelected() ?
        this.selection.clear() :
        this.item.data.forEach(row => this.selection.select(row));
  }

  logSelection() {
    this.selection.selected.forEach(s => console.log(s));
  }

  viewItem(id) {
    this.rout.navigate(['/item/'+id])
  }
}
