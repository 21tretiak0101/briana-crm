import {Injectable} from '@angular/core';
import {ImageService} from '../image.service';
import {Observable} from 'rxjs';
import {MaterialService} from '../../material/material.service';
import {ProductService} from '../../product/product.service';

@Injectable({
  providedIn: 'root'
})
export class ProductImageService extends ImageService {
  constructor(private productService: ProductService) {
    super();
  }

  error(error: any): void {
    MaterialService.error(error);
  }

  remove(): Observable<any> {
    return this.productService.removeImage();
  }

  upload(image: File): Observable<any> {
    return this.productService.uploadImage(image);
  }
}
