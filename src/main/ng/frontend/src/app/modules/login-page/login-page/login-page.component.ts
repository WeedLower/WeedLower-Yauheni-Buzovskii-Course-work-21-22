import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserModel} from "../../../model/usermodel";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {AuthService} from "../../../service/auth/auth.service";
import {UserService} from "../../../service/user/user.service";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  hide = true;
  user: UserModel = new UserModel();
  formControl: FormGroup;

  constructor(private http: HttpClient, private router: Router, public auth:AuthService, private userService: UserService,
              private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.initReactForm()
  }
  private initReactForm():void{
    this.formControl = this.formBuilder.group({
      email: ['', Validators.email],
      password: ['',Validators.required]
    });
  }

  onSubmit(){
    this.auth.signIn(this.user);
  }

}
