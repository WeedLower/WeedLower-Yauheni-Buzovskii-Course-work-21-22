import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-create-item-dialog',
  templateUrl: './create-item-dialog.component.html',
  styleUrls: ['./create-item-dialog.component.scss']
})
export class CreateItemDialogComponent implements OnInit{

  formControl: FormGroup;

  constructor( public dialogRef: MatDialogRef<CreateItemDialogComponent>,
               @Inject(MAT_DIALOG_DATA) public data: any,
               private formBuilder: FormBuilder) {}

  ngOnInit():void {
    this.initReactForm();
  }

  noClick(): void {
    this.dialogRef.close();
  }

  private initReactForm() {
    this.formControl=this.formBuilder.group({
      mes:['',Validators.required]
    })}
}
