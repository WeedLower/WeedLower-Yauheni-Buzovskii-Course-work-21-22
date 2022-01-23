import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ItemModel} from "../../model/item";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient) { }

  search(searchString: string):Observable<ItemModel[]>{
    return this.http.get<ItemModel[]>("/api/search/"+searchString);
  }
}
