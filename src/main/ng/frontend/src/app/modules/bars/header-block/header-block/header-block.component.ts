import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../../../service/auth/auth.service";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header-block',
  templateUrl: './header-block.component.html',
  styleUrls: ['./header-block.component.css']
})
export class HeaderBlockComponent implements OnInit{

  current=false;
  roleCurrent=false;
  name:any;
  role:any;
  constructor(private auth: AuthService,private http:HttpClient,private router:Router) {
  }

  ngOnInit(): void{
    if (this.auth.user!=null && this.auth.user.role.toString()=="ADMIN"){
      this.roleCurrent=true;
    }
    if (this.auth.user==null){
    this.current=false;
    }else{
      this.current=true;
      this.name=this.auth.user.name;
    }
  }

  logOut(){
    if (confirm('Exit?')){
      this.auth.logOut();
    }
  }
}
