import {ChangeDetectionStrategy,EventEmitter, Component, Input, Output} from '@angular/core';
import {MatSlideToggleChange} from "@angular/material/slide-toggle";


@Component({
  selector: 'app-footer-block',
  templateUrl: './footer-block.component.html',
  styleUrls: ['./footer-block.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class FooterBlockComponent {

  @Input()
  isDarkMode = false;

  @Output()
  readonly darkModeSwitched = new EventEmitter<boolean>();

  onDarkModeSwitched({ checked }: MatSlideToggleChange) {
    this.darkModeSwitched.emit(checked);
  }
}
