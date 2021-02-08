import {Injectable} from '@angular/core';
import {ImageService} from '../image.service';
import {Observable} from 'rxjs';
import {ProductService} from '../../product/product.service';

@Injectable()
export class ProductImageService extends ImageService {
  constructor(private productService: ProductService) {
    super();
  }

  remove(): Observable<any> {
    return this.productService.removeImage();
  }

  upload(image: File): Observable<any> {
    return this.productService.uploadImage(image);
  }
}
