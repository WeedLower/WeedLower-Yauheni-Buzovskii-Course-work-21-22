import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FooterBlockComponent } from './footer-block/footer-block.component';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatOptionModule} from "@angular/material/core";
import {MatSelectModule} from "@angular/material/select";
import {MatSlideToggleModule} from "@angular/material/slide-toggle";



@NgModule({
  declarations: [
    FooterBlockComponent
  ],
  imports: [
    CommonModule,
    MatToolbarModule,
    MatOptionModule,
    MatSelectModule,
    MatSlideToggleModule
  ],
  exports:[FooterBlockComponent]
})
export class FooterBlockModule { }

