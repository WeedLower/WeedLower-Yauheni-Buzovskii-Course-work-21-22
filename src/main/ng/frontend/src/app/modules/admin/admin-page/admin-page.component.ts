import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../../../service/auth/auth.service";
import {UserService} from "../../../service/user/user.service";
import {UserModel} from "../../../model/usermodel";
import {SelectionModel} from "@angular/cdk/collections";
import {MatTableDataSource} from "@angular/material/table";

@Component({
    selector: 'app-admin-page',
    templateUrl: './admin-page.component.html',
    styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {

    users=new MatTableDataSource<UserModel>();
    displayedColumns:string[] = ['select','id','name','email','role','status','view']
    selection = new SelectionModel<UserModel>(true, []);
    constructor(private rout: Router,private auth:AuthService,private userService:UserService) { }

    ngOnInit(): void {
        if (this.auth.user==null || this.auth.user.role.toString()!="ADMIN"){
            this.rout.navigate(['/'])
        }else {
            this.auth.check();
            this.getAllUsers();
        }
    }

    private getAllUsers() {
        this.userService.getUsers().subscribe(data=>{
                this.users.data=data as UserModel[];
            },
            error => console.log(error));
    }

    delete():void{
        if (confirm('Delete user / users?')){
            this.selection.selected.forEach(i=>
                this.userService.delete(i.id).subscribe(()=>{
                        this.getAllUsers();
                    },
                    error=> console.log(error)));
        }
    }

    block():void{
        if (confirm('Block user / users?')){
            this.selection.selected.forEach(i =>
                this.userService.block(i.id).subscribe(()=>{
                        this.getAllUsers();
                    },
                    error=> console.log(error)));
        }
    }

    unblock():void{
        if (confirm('Unblock user / users?')){
            this.selection.selected.forEach(i =>
                this.userService.unblock(i.id).subscribe(()=>{
                        this.getAllUsers();
                    },
                    error=> console.log(error)));
        }
    }

    setAdmin():void{
        if (confirm('Set role ADMIN to user / users?')){
            this.selection.selected.forEach((i) =>
                i == this.auth.user? this.auth.logOut() :
                    this.userService.setRoleAdmin(i.id).subscribe(()=>{
                            this.getAllUsers();
                        },
                        error=> console.log(error)));
        }
    }

    setUser():void{
        if (confirm('Set role USER to user / users?')){
            this.selection.selected.forEach(i =>
                this.userService.setRoleUser(i.id).subscribe(()=>{
                        this.getAllUsers();
                    },
                    error=> console.log(error)));
        }
    }

    isAllSelected() {
        const numSelected = this.selection.selected.length;
        const numRows = this.users.data.length;
        return numSelected === numRows;
    }

    masterToggle() {
        this.isAllSelected() ?
            this.selection.clear() :
            this.users.data.forEach(row => this.selection.select(row));
    }

    logSelection() {
        this.selection.selected.forEach(s => console.log(s));
    }

    viewUser(id) {
        this.rout.navigate(['/profile/'+id])
    }

}
