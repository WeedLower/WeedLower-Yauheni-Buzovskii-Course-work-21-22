import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderBlockComponent } from './header-block/header-block.component';




@NgModule({
  declarations: [
    HeaderBlockComponent
  ],
  imports: [
    CommonModule
  ],
  exports:[HeaderBlockComponent]
})
export class HeaderBlockModule { }
