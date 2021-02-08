import {Component} from '@angular/core';

@Component({
  selector: 'app-responsive-circular-loader',
  template: `
    <app-circular-loader *ngIf="isLoading"></app-circular-loader>
  `
})
export class ResponsiveCircularLoaderComponent {

  isLoading = false;

  startLoading(): void {
    this.isLoading = true;
  }

  endLoading(): void {
    this.isLoading = false;
  }
}
