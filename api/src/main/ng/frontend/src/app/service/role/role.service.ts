import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Rolemodel} from "../../model/rolemodel";

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  constructor(private http: HttpClient) { }

  // getRole(): Observable<Rolemodel[]>{
  //   return this.http.get<Rolemodel[]>('/api/roles')
  // }

  getRoleById(id: number):Observable<Rolemodel>{
    return this.http.post<Rolemodel>('/api/roles/id',id)
  }
}
