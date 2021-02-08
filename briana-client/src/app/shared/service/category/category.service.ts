import {Injectable} from '@angular/core';
import {Observable, of, throwError} from 'rxjs';
import {Category} from '../../entities';
import {delay, switchMap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  categories: Category[] = [
    {
      id: '10',
      name: 'Fruits',
      description: 'All world best fruits in one place!'
    },
    {
      id: '12',
      name: 'Drinks',
      description: 'All world best drinks in one place!'
    }
  ];

  getAll(): Observable<Category[]> {
    return of(this.categories);
  }

  getById(id: string): Observable<Category> {
    return new Observable<Category>((observer) => {
      const category$ = this.categories.find(cat => cat.id === id);
      if (category$) {
        observer.next(category$);
      } else {
        observer.error(new Error('Category with this id not found'));
      }
    });
  }

  create(category: Category, image?: File): Observable<Category> {
    return of(category).pipe(delay(1000));
  }

  remove(id: string): Observable<any> {
    return of('ok');
  }

  removeImage(): Observable<string> {
    return of('ok').pipe(delay(2000));
  }

  uploadImage(image: File): Observable<string> {
    return of('ok').pipe(delay(2000));
  }

  update(category: Category): Observable<Category> {
     return of(category).pipe(
       delay(2000),
       switchMap(() => throwError(new Error('Server error. Try again!'))));
  }
}
