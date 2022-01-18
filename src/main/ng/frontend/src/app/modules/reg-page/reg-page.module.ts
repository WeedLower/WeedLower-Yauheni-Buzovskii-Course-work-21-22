import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegPageComponent } from './reg-page/reg-page.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";



@NgModule({
  declarations: [
    RegPageComponent
  ],
  imports: [
    CommonModule,
      FormsModule,
      ReactiveFormsModule
  ],
  exports:[RegPageComponent]
})
export class RegPageModule { }
