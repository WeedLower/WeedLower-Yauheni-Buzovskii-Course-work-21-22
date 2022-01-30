import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../../../../service/auth/auth.service";
import {ItemsService} from "../../../../../service/collections/items.service";
import {ItemModel} from "../../../../../model/item";
import {CommentsService} from "../../../../../service/comments/comments.service";
import {CommentsModel} from "../../../../../model/comments";
import {MatDialog} from "@angular/material/dialog";
import {CreateItemDialogComponent} from "./create-item-dialog/create-item-dialog.component";
import {interval, startWith, Subscription, switchMap} from "rxjs";

@Component({
  selector: 'app-view-item',
  templateUrl: './view-item.component.html',
  styleUrls: ['./view-item.component.css']
})
export class ViewItemComponent implements OnInit,OnDestroy {

  id: number;
  statusUser = false;
  itemMod: ItemModel;
  comments: CommentsModel[];
  newComment:CommentsModel = new CommentsModel();
  mes:string;
  statusLikes=false;
  timeInterval:Subscription;

  constructor(private route: ActivatedRoute, private auth: AuthService,private rout:Router,
              private itm: ItemsService, private comment: CommentsService,
              private dialog:MatDialog) {}

  ngOnInit(): void {
    this.id = +this.route.snapshot.params['id'];
    this.timeInterval = interval(3000).pipe(
        startWith(0),
        switchMap(()=> this.comment.getAllCommentByItemId(this.id)))
        .subscribe(res=>{
          this.comments=res;
        } ,error => console.log(error));
    this.getItemById(this.id)
    if (this.auth.user != null) {
      this.statusUser = true;
    }
  }

  ngOnDestroy(): void {
    this.timeInterval.unsubscribe();
  }

  like(){
    this.statusLikes=true;
    this.itm.like(this.itemMod.id).subscribe(()=>{
          this.getItemById(this.id);
        },
        error=> console.log(error)
    );
  }

  getItemById(id: number): any {
    this.itm.getById(id).subscribe(data => {
          this.itemMod = data;
        },
        error => console.log(error));
  }

  private saveNewComment(mes:string):any{
    this.newComment.comment=mes;
    this.newComment.item=this.itemMod;
    this.newComment.owner=this.auth.user;
    this.comment.save(this.newComment).subscribe(data=>{
      console.log(data);
    },error => console.log(error));

  }

  openDialog():void {
    let dialogRef = this.dialog.open(CreateItemDialogComponent, {
      width: '250px',
      data:{mes:this.mes}
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.mes = result;
      if (this.mes!=null){
        this.saveNewComment(this.mes);
      }
    });
  }
}