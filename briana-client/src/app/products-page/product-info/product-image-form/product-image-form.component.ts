import {Component, Input} from '@angular/core';
import {ProductImageService} from '../../../shared/service/image/product-image/product-image.service';
import {ImageService} from '../../../shared/service/image/image.service';

@Component({
  selector: 'app-product-image-form',
  template: `
    <app-generic-image-handler [source]="source"></app-generic-image-handler>
  `,
  viewProviders: [{
    provide: ImageService, useClass: ProductImageService
  }]
})
export class ProductImageFormComponent {
  @Input() source: string;
}
