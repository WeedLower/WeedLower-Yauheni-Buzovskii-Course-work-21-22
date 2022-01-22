import { Component, OnInit } from '@angular/core';
import {UserModel} from "../../../model/usermodel";
import {Rolemodel} from "../../../model/rolemodel";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {RoleService} from "../../../service/role/role.service";
import {UserService} from "../../../service/user/user.service";
import {AuthService} from "../../../service/auth/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-reg-page',
  templateUrl: './reg-page.component.html',
  styleUrls: ['./reg-page.component.css']
})
export class RegPageComponent implements OnInit {

  newUser: UserModel = new UserModel();
  public roles: Rolemodel;
  public userExistByEmail: boolean = false;
  public notification: boolean=false;
  public users: UserModel[];
  formControl: FormGroup;



  constructor(private roleService: RoleService, private userService: UserService,private auth: AuthService,
              private router: Router,private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.initReactForm();
    if (this.auth.user == null){

    } else {

    }
  }

  initReactForm(): void{
    this.formControl = this.formBuilder.group({
      name: ['', Validators.required],
      surname:['', Validators.required],
      email:['',Validators.email],
      password:['', Validators.required,]
    });
  }

  public register(){
    this.auth.regUser(this.newUser);
  }

  public ifExistsByEmail(email: string): void{
    this.userService.findByEmail(email).subscribe((exists) =>{
      if (exists){
        this.userExistByEmail=true;
      }else {
        this.userExistByEmail=false;
      }
      if (!this.userExistByEmail){
        this.register();
        this.newUser.name='';
        this.newUser.surname='';
        this.newUser.email='';
        this.newUser.password='';
        this.notification = false;
      } else {
        this.notification = true;
      }
    });
  }
}
