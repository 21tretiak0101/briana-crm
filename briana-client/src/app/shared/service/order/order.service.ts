import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {Order} from '../../entities';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  orders: Order[] = [
    {
      id: 21,
      date: new Date(),
      products: [
        {
          name: 'Apple',
          price: 300,
          category: {
            id: '10',
            name: 'Fruits'
          }
        },
        {
          name: 'Water',
          price: 200,
          category: {
            id: '12',
            name: 'Drinks'
          }
        }
      ]
    }
  ];

  getAll(params: any = {}): Observable<Order[]> {
    return of(this.orders);
  }
}
