import {ChangeDetectionStrategy, EventEmitter, Component, Input, Output, OnInit} from '@angular/core';
import {MatSlideToggleChange} from "@angular/material/slide-toggle";
import {TranslateService} from "@ngx-translate/core";

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

  constructor(public translate:TranslateService) {

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

  ngOnInit(): void {
    this.translate.addLangs(['en','ru']);
    this.translate.setDefaultLang('en');
    // if (localStorage.getItem('locale')){
      const browserLang = localStorage.getItem('locale');
      this.translate.use(browserLang.match(/en|ru/)? browserLang : 'en');
    // }else {
      localStorage.setItem('locale','en');
    }
  //}
}
