import {Component, Input} from '@angular/core';
import {Category} from '../../shared/entities';
import {DEFAULT_PRODUCT_IMAGE_PATH} from '../../../environments/environment';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html'
})
export class CategoryListComponent {
  @Input() categories: Array<Category>;
  defaultImage: string = DEFAULT_PRODUCT_IMAGE_PATH;
}
