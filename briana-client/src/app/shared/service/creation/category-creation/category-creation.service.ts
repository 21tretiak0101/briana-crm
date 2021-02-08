import {Injectable} from '@angular/core';
import {CreationService} from '../creation.service';
import {Observable} from 'rxjs';
import {CategoryService} from '../../category/category.service';

@Injectable()
export class CategoryCreationService extends CreationService {
  constructor(private categoryService: CategoryService) {
    super();
  }

  navigationCommands(): string[] {
    return ['/categories'];
  }

  save(entity: any, file?: File): Observable<any> {
    return this.categoryService.create(entity, file);
  }
}
