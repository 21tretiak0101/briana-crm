import {Injectable} from '@angular/core';
import {ImageService} from '../image.service';
import {Observable} from 'rxjs';
import {CategoryService} from '../../category/category.service';

@Injectable()
export class CategoryImageService extends ImageService {
  constructor(private categoryService: CategoryService) {
    super();
  }

  remove(): Observable<any> {
    return this.categoryService.removeImage();
  }

  upload(image: File): Observable<any> {
    return this.categoryService.uploadImage(image);
  }
}
