import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {TagModel} from "../../model/tag";

@Injectable({
  providedIn: 'root'
})
export class TagService {

  constructor(private http: HttpClient) { }

  getAll():Observable<TagModel[]>{
    return this.http.get<TagModel[]>("/api/tags/")
  }
}
