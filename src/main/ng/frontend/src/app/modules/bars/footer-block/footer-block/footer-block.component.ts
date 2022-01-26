import {ChangeDetectionStrategy,EventEmitter, Component, Input, Output} from '@angular/core';
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
export class FooterBlockComponent {

  selectedValue: string;

  constructor(public translate:TranslateService) {
    translate.addLangs(['en','ru']);
    if (localStorage.getItem('locale')){
      const browserLang = localStorage.getItem('locale');
      translate.use(browserLang.match(/en|ru/)? browserLang : 'en');
    }else {
      localStorage.setItem('locale','en');
      translate.setDefaultLang('en');
    }
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
