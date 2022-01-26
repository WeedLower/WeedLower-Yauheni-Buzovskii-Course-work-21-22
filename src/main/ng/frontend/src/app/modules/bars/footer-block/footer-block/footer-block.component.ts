import {ChangeDetectionStrategy, EventEmitter, Component, Input, Output, OnInit} from '@angular/core';
import {MatSlideToggleChange} from "@angular/material/slide-toggle";
import {TranslateService} from "@ngx-translate/core";
import { AuthService } from 'src/app/service/auth/auth.service';

interface Lang {
  viewValue: string;
  value:string;
}

@Component({
  selector: 'app-footer-block',
  templateUrl: './footer-block.component.html',
  styleUrls: ['./footer-block.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class FooterBlockComponent implements OnInit{

  selectedValue: string;


  constructor(public translate:TranslateService,private auth:AuthService) {
    translate.addLangs(['en','ru']);
    translate.setDefaultLang('en');
    const browserLang = translate.getBrowserLang()
    translate.use(browserLang.match(/en|ru/)? browserLang : 'en');
  }

  ngOnInit(): void {


    // if (this.auth.user!=null){
    //   if (localStorage.getItem('locale')){
    //   }else {
    //     localStorage.setItem('locale','en');
    //   }
    // }
  }


  @Input()
  isDarkMode = false;

  @Output()
  readonly darkModeSwitched = new EventEmitter<boolean>();

  onDarkModeSwitched({ checked }: MatSlideToggleChange) {
    this.darkModeSwitched.emit(checked);
  }

  switchLanguage(lang: string) {
    localStorage.setItem('locale',lang);
    this.translate.use(lang);
  }
}
