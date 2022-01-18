import { Injectable } from '@angular/core';
import {AuthuserModel} from "../../model/authuser";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {AuthToken, UserModel} from "../../model/usermodel";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public user: AuthuserModel;
  public token: string;

  public authError: boolean=false;
  public regError: boolean=false;

  constructor(private http: HttpClient,
              private router: Router) {
    let user = JSON.parse(localStorage.getItem('user'));
    let token = localStorage.getItem('token');
    if (user && token){
      this.user=user;
      this.token=token;
    }else {
      this.user=null;
      this.token="";
    }
  }

  public getToken(user: UserModel): Observable<AuthToken>{
    return this.http.post<AuthToken>("/api/users/auth/generate-token",user);
  }

  public regNewUser(user: UserModel): Observable<AuthToken>{
    return this.http.post<AuthToken>('/api/users/auth/sign-up/',user);
  }

  public userAuth():Observable<UserModel>{
    return this.http.get<UserModel>("/api/users/auth/user");
  }

  // public logout(): void{
  //   return this.http.get("/logout");
  // }

  public signIn(authUser: UserModel):void{
    this.getToken(authUser).subscribe(data =>{
      this.token = data.token;
      this.userAuth().subscribe(data =>{
        this.user=data;
        localStorage.setItem("user", JSON.stringify(this.user));
        localStorage.setItem("token", this.token);
        this.router.navigate(['/profile']);
      },error => {
        this.authError = true;
      })
    },error => {
      this.authError = true;
    })
  }

  public regUser(authUser: UserModel): void{
    this.regNewUser(authUser).subscribe(data =>{
      this.router.navigate(['/login'])
    }, error => {
      this.regError=true;
    })
  }

  public logOut():void{
    localStorage.clear();
    this.user=null;
    this.token="";
    this.router.navigate(['/']);
    setTimeout(location.reload.bind(location), 100);
    // this.logout();
  }
}
