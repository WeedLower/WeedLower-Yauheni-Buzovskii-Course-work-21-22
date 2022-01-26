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
  roles: Rolemodel;
  userExistByEmail: boolean = false;
  notification: boolean=false;
  users: UserModel[];
  formControl: FormGroup;

  constructor(private roleService: RoleService, private userService: UserService,private auth: AuthService,
              private router: Router,private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.initReactForm();
    if (this.auth.user == null){
    } else {
      this.router.navigate(['/'])
    }
  }

  private initReactForm(): void{
    this.formControl = this.formBuilder.group({
      name: ['', Validators.required],
      surname:['', Validators.required],
      email:['',Validators.email],
      password:['', Validators.required,]
    });
  }

  private register(){
    this.auth.regUser(this.newUser);
  }

  ifExistsByEmail(email: string): void{
    this.userService.findByEmail(email).subscribe((exists) =>{
      this.userExistByEmail = !!exists;
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
