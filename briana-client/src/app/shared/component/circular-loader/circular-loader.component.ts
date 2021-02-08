import {Component} from '@angular/core';

@Component({
  selector: 'app-circular-loader',
  styles: ['.spinner-blue-only { border-color: #9575cd !important}'],
  template: `
      <div class="preloader-wrapper small active">
        <div class="spinner-layer spinner-blue-only">
          <div class="circle-clipper left">
            <div class="circle"></div>
          </div>
          <div class="gap-patch">
            <div class="circle">
            </div>
          </div>
          <div class="circle-clipper right">
            <div class="circle"></div>
          </div>
        </div>
      </div>
  `
})
export class CircularLoaderComponent { }
