import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginPageComponent } from './login-page/login-page.component';
import {UserService} from "../../service/user/user.service";
import {RoleService} from "../../service/role/role.service";
import {AuthService} from "../../service/auth/auth.service";
import {InterceptorService} from "../../service/auth/interceptor.service";
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import {MatInputModule} from "@angular/material/input";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";



@NgModule({
  declarations: [
    LoginPageComponent
  ],
  imports: [
    CommonModule,
      FormsModule,
      ReactiveFormsModule,
      MatFormFieldModule,
      MatSelectModule,
      MatInputModule
  ],
  exports:[LoginPageComponent],
  providers:[
      UserService,
      RoleService,AuthService,InterceptorService,{provide: HTTP_INTERCEPTORS,useClass:InterceptorService,multi:true}
  ]
})
export class LoginPageModule { }
