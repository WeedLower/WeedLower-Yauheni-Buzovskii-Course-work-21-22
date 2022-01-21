import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CollectionModel} from "../../model/collections";
import {Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class CollectionsService {

  constructor(private http: HttpClient) { }

  getAll():Observable<CollectionModel[]>{
    return this.http.get<CollectionModel[]>('/api/collections');
  }

  findAllByUser(id: number): any{
    return this.http.get("/api/collections/user/"+id);
  }

  delete(id): Observable<any>{
    return  this.http.delete("/api/collections/delete/"+id);
  }

  saveNewCol(newColl: CollectionModel): Observable<CollectionModel>{
    return this.http.post<CollectionModel>('/api/collections',newColl);
  }

  findCollectById(id: number):any{
    return this.http.get("api/collections/"+id);
  }

    editCollection(newColl: CollectionModel):any {
        return this.http.post("/api/collections/edit/",newColl)
    }
}
