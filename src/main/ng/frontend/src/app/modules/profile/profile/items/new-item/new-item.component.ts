import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../../../../service/auth/auth.service";
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {ItemModel} from "../../../../../model/item";
import {ItemsService} from "../../../../../service/collections/items.service";
import {CollectionsService} from "../../../../../service/collections/collections.service";
import {CollectionModel} from "../../../../../model/collections";
import {TagModel} from "../../../../../model/tag";
import {TagService} from "../../../../../service/tag/tag.service";
import {COMMA, ENTER} from "@angular/cdk/keycodes";
import {map, Observable, startWith} from "rxjs";
import {MatChipInputEvent} from "@angular/material/chips";
import {MatAutocompleteSelectedEvent} from "@angular/material/autocomplete";
import {DatePipe} from "@angular/common";



@Component({
  selector: 'app-new-item',
  templateUrl: './new-item.component.html',
  styleUrls: ['./new-item.component.scss']
})
export class NewItemComponent implements OnInit {

  formGroup: FormGroup;
  newItem:ItemModel = new ItemModel();
  collect: CollectionModel = new CollectionModel();
  allTags:TagModel[] = [];
  tagString:string[] = []
  allTagsString:string[] = [];
  filtredString: Observable<string[]>
  id:number;
  updateMod=false;
  itemId:number;
  separatorKeysCodes: number[] = [ENTER,COMMA];
  formControl = new FormControl();
  checkbox=[true,false];
  date:Date = new Date;



  @ViewChild('tagInput') tagInput: ElementRef<HTMLInputElement>;

  constructor(private route: ActivatedRoute,private rout:Router,private auth:AuthService,
              private fB:FormBuilder,private item:ItemsService,private col: CollectionsService,
              private tagService:TagService,private datePipe:DatePipe) {
    this.datePipe.transform(this.date,"yyyy-MM-dd")
  }

  ngOnInit(): void {
    this.auth.check();
    this.initReactForm();
    this.id= +this.route.snapshot.params['id'];
    this.route.snapshot.params['itemId']!= null || NaN ? this.getItem(+this.route.snapshot.params['itemId']) : true;
    if(this.auth.user==null){
      this.rout.navigate(['/'])
    }else {
      this.getCol(this.id);
      this.getAllTags();
      this.filtredString = this.formControl.valueChanges.pipe(
          startWith(null),
          map((tag: string | null) => (tag ? this._filter(tag) : this.allTagsString.slice())),
      );
    }
  }



  private initReactForm() {
    this.formGroup = this.fB.group({
      name:['',Validators.required],
      number1:[''],
      number2:[''],
      number3:[''],
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

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();
    if (value) {
      this.tagString.push(value);
    }
    event.chipInput!.clear();
    this.formControl.setValue(null);
  }

  remove(tag: string): void {
    const index = this.tagString.indexOf(tag);
    if (index >= 0) {
      this.tagString.splice(index, 1);
    }
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    this.tagString.push(event.option.viewValue);
    this.tagInput.nativeElement.value = '';
    this.formControl.setValue(null);
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.allTagsString.filter(tag => tag.toLowerCase().includes(filterValue));
  }

  getCol(id:number): any{
    this.col.findCollectById(this.id).subscribe(data=>{
      this.newItem.collection=data;
    })
  }

  getItem(id:number) {
    this.item.getById(id).subscribe(data=>{
      this.newItem=data as ItemModel;
      this.tagString=this.newItem.tags;
      this.updateMod=true;
      console.log(this.newItem)
    })
  }

  private getAllTags() {
    this.tagService.getAll().subscribe(data => {
          console.log(data);
          this.allTags=data as TagModel[];
          data.forEach(s=>this.allTagsString.push(s.tag))
        },
        error=>console.log(error))
  }

  create(): any {
    this.newItem.author=this.auth.user;
    this.newItem.tags=this.tagString;
    this.item.saveNewItem(this.newItem).subscribe(data=>{
      console.log(data);
      this.rout.navigate(['/profile/collect/'+this.id])
    })
  }

  edit(): any {
    this.item.update(this.newItem).subscribe(data=>{
      console.log('edit',data)
      this.rout.navigate(['/profile/collect/'+this.id])
    },
        error => console.log(error))
  }

}
