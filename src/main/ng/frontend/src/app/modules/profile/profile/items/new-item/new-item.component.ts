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
  tagString:string[] = ['books']
  allTagsString:string[] = [];
  filtredString: Observable<string[]>
  id:number;
  separatorKeysCodes: number[] = [ENTER,COMMA];
  formControl = new FormControl();

  @ViewChild('tagInput') tagInput: ElementRef<HTMLInputElement>;
  constructor(private route: ActivatedRoute,private rout:Router,private auth:AuthService,
              private fB:FormBuilder,private item:ItemsService,private col: CollectionsService,
              private tagService:TagService) {}

  ngOnInit(): void {
    this.initReactForm();
    this.id= +this.route.snapshot.params['id'];
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
      name:['',Validators.required]
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
}
