import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ItemModel} from "../../model/item";
import {Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class ItemsService {

  constructor(private http: HttpClient) { }

  getAllItemsByCollection(id: number):any {
    return this.http.get("/api/items/collect/"+id);
  }

  saveNewItem(newItem: ItemModel):Observable<ItemModel> {
    return this.http.post<ItemModel>('/api/items/save',newItem);
  }

  delete(id):Observable<any>{
    return  this.http.delete("/api/items/delete/"+id);
  }

  getAll():Observable<ItemModel[]> {
    return this.http.get<ItemModel[]>('/api/items');
  }

  getById(id:number):any{
    return this.http.get("/api/items/"+id);
  }

  like(id:number):any{
    return this.http.post('/api/items/like',id);
  }

  findItemsByTagId(id: number):Observable<ItemModel[]> {
    return this.http.get<ItemModel[]>("/api/items/tag/"+id);
  }

  update(newItem: ItemModel):any{
    return this.http.post<any>('/api/items/update',newItem);
  }
}
