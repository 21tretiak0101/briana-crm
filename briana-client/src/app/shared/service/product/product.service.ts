import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {Product} from '../../entities';
import {delay} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  products: Array<Product> = [
    {
      id: '1',
      name: 'Apple',
      price: 1.98,
      description: 'An apple is an edible fruit produced by an apple tree',
      category: {
        id: '10',
        name: 'Fruits'
      },
    },
    {
      id: '2',
      name: 'Water',
      price: 0.70,
      description: 'Water is an inorganic, transparent substance',
      category: {
        id: '12',
        name: 'Drinks'
      }
    }
  ];

  getAll(): Observable<Product[]> {
    return of(this.products).pipe(delay(1000));
  }

  getById(id: string): Observable<Product> {
    return new Observable<Product>((observer) => {
      const product$ = this.products.find(pr => pr.id === id);
      if (product$) {
        observer.next(product$);
      } else {
        observer.error(new Error('Product not found'));
      }
    });
  }

  removeImage(): Observable<any> {
    return of('deleted').pipe(delay(2000));
  }

  remove(id: string): Observable<any> {
    this.products = this.products.filter(product => product.id !== id);
    return of('deleted');
  }

  update(product: Product): Observable<Product> {
    return of(product).pipe(delay(2000));
  }

  create(product: Product, image?: File): Observable<Product> {
    return of(product).pipe(delay(2000));
  }

  uploadImage(file: File): Observable<string> {
    return of('ok').pipe(delay(2000));
  }
}
