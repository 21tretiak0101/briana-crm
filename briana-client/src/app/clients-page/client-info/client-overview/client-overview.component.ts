import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-client-overview',
  template: `
    <div class="center">
      <img alt="" class="default-image" [src]="'/assets/analytics.png'">
    </div>
  `
})
export class ClientOverviewComponent {
  @Input() clientId: number;
}
