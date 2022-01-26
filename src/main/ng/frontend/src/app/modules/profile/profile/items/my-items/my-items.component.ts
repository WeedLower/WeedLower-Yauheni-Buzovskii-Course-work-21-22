import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {CollectionsService} from "../../../../../service/collections/collections.service";
import {CollectionModel} from "../../../../../model/collections";
import {ItemsService} from "../../../../../service/collections/items.service";
import {ItemModel} from "../../../../../model/item";
import {MatSort} from "@angular/material/sort";
import {SelectionModel} from "@angular/cdk/collections";
import {MatTableDataSource} from "@angular/material/table";
import {AuthService} from "../../../../../service/auth/auth.service";




@Component({
  selector: 'app-my-items',
  templateUrl: './my-items.component.html',
  styleUrls: ['./my-items.component.css']
})
export class MyItemsComponent implements OnInit,AfterViewInit {
  collection: CollectionModel;
  newItem: ItemModel = new ItemModel();
  id: number;
  status=true;
  custom=false;
  selection = new SelectionModel<ItemModel>(true, []);
  item = new MatTableDataSource<ItemModel>()
  displayedColumns: string[] = ['select','id','name','tags'];


  constructor(private route: ActivatedRoute, private collectionsService: CollectionsService,
              private itemsService: ItemsService,private auth:AuthService,
              private rout: Router) {
    this.id = +this.route.snapshot.params['id'];
    if(this.id!=null){
      this.itemsService.getAllItemsByCollection(this.id).subscribe((items) => {
            this.item.data = items as ItemModel[];
          })}
  }

  ngOnInit(): void {
    this.auth.check();
    if (this.auth.user.role.toString()=='ADMIN'){
    }
    if (this.id != null) {
      this.getCollect(this.id)
    }
  }

  @ViewChild(MatSort) sort: MatSort;

  ngAfterViewInit() {
    this.item.sort = this.sort;
  }

  private delEmptyColumns(collection : CollectionModel){
    const entries = Object.entries(collection);
    entries.forEach(([key, value]) => {
      value!=null && key!='user' && key!='items' && key!='image' && key!='id' && key!='name' && key!='description' && key!='topic'
          ? this.displayedColumns.push(key): true;
    })
      this.displayedColumns.push('button');
  }


  private getCollect(id): void {
    this.collectionsService.findCollectById(id).subscribe((data) =>{
      data.user.id!=this.auth.user.id && this.auth.user.role.toString()!="ADMIN" ? this.rout.navigate(['/']) :
      this.collection = data as CollectionModel;
      this.delEmptyColumns(this.collection);
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
          this.itemsService.delete(id.id).subscribe(()=>{
            console.log("collection deleted");
            this.getCollectItems(this.id);
          },
              error=> console.log(error)));
    }
  }

  openDialog() {
    this.rout.navigate(['profile/collect/' + this.id + '/new-item'])
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

  updateItem(itemId) {
    this.rout.navigate(['profile/collect/'+this.id+'/item/'+itemId])
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.item.filter = filterValue.trim().toLowerCase();
  }

}
