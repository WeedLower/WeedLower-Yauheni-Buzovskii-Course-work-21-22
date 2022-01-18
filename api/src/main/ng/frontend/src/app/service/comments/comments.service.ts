import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CommentsModel} from "../../model/comments";

@Injectable({
  providedIn: 'root'
})
export class CommentsService {

  constructor(private http:HttpClient) { }

  getAllCommentByItemId(id:number):Observable<CommentsModel[]>{
    return this.http.get<CommentsModel[]>("/api/comments/item/"+id);
  }

  save(newComment: CommentsModel):Observable<CommentsModel> {
    return this.http.post<CommentsModel>('/api/comments',newComment);
  }
}
