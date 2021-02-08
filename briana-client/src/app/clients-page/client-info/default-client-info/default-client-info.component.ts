import {Component} from '@angular/core';

@Component({
  selector: 'app-default-client-info',
  styleUrls: ['./default-client-info.component.css'],
  template: `
    <div class="center">
      <img [src]="'/assets/women.png'" alt="" class="women">
    </div>
  `
})
export class DefaultClientInfoComponent { }
