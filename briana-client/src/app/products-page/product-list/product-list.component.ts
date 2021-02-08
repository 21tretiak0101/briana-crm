import {Component, Input} from '@angular/core';
import {Product} from '../../shared/entities';
import {DEFAULT_PRODUCT_IMAGE_PATH} from '../../../environments/environment';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent {
  @Input() products: Product[];
  defaultImage: string = DEFAULT_PRODUCT_IMAGE_PATH;
}
