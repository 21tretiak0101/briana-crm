import {Injectable} from '@angular/core';
import {CreationService} from '../creation.service';
import {ProductService} from '../../product/product.service';
import {Observable} from 'rxjs';
import {Product} from '../../../entities';

@Injectable()
export class ProductCreationService extends CreationService {

  constructor(private productService: ProductService) {
    super();
  }

  navigationCommands(): string[] {
    return ['/products'];
  }

  save(entity: Product, file?: File): Observable<Product> {
    return this.productService.create(entity, file);
  }
}
