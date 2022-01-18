import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserModel} from "../../model/usermodel";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  findByEmail(email: string): any {
    return this.http.get("/api/users/email/"+email);
  }

  getUsers(): Observable<UserModel[]>{
    return this.http.get<UserModel[]>('/api/users');
  }

    delete(id: string):Observable<any> {
    return this.http.delete("/api/users/"+id);
    }

  block(id: string):Observable<any> {
    return this.http.get("/api/users/block/"+id)
  }

  unblock(id: string):Observable<any> {
    return this.http.get("/api/users/unblock/"+id)
  }

  setRoleAdmin(id: string):Observable<any> {
    return this.http.get("/api/users/setadmin/"+id)
  }

  setRoleUser(id: string):Observable<any> {
    return this.http.get("/api/users/setuser/"+id)
  }

    getUser(id: any):Observable<UserModel> {
        return this.http.get<UserModel>("/api/users/"+id)
    }
}
