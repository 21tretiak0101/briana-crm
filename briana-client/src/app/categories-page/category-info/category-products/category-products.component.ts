import {Component, Input} from '@angular/core';
import {Product} from '../../../shared/entities';
import {DEFAULT_PRODUCT_IMAGE_PATH} from '../../../../environments/environment';

@Component({
  selector: 'app-category-products',
  templateUrl: './category-products.component.html'
})
export class CategoryProductsComponent {
  @Input() products: Product[];
  defaultImage = DEFAULT_PRODUCT_IMAGE_PATH;
}
