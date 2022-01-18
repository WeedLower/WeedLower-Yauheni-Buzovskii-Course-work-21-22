import {Component, Inject, OnInit, Renderer2} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {DOCUMENT} from "@angular/common";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: [`./app.component.css`]
})
export class AppComponent implements OnInit{
    title='frontend';
    get isDarkMode():boolean{
        return this.currentTheme === 'theme-dark'
    }

    private currentTheme='theme-light';

    constructor(private http:HttpClient,
    @Inject(DOCUMENT)private docement: Document,private render: Renderer2) {
    }

    ngOnInit():void {
        this.currentTheme = localStorage.getItem('activeTheme') || 'theme-light';
        this.render.setAttribute(this.docement.body,'class',this.currentTheme);
    }

    switchMode(isDarkMode: boolean){
        this.currentTheme = isDarkMode ? 'theme-dark' : 'theme-light';
        this.render.setAttribute(this.docement.body,'class',this.currentTheme);
        localStorage.setItem('activeTheme',this.currentTheme);
    }
}