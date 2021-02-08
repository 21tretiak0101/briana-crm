import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {Client} from '../../entities';
import {delay} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  clients: Array<Client> = [
    {
      id: 212431,
      name: 'Eydie Casbolt',
      email: 'ecasbolto@smugmug.com',
      phone: '+907463254704',
      address: {
        country: 'Germany',
        city: 'Berlin',
        postcode: '320-1234'
      },
      orders: [
        {
          id: 21,
          date: new Date(),
          products: [
            {
              name: 'Apple',
              price: 1.20,
              category: {
                id: '10',
                name: 'Fruits'
              }
            },
            {
              name: 'Water',
              price: 0.12,
              category: {
                id: '12',
                name: 'Drinks'
              }
            }
          ]
        }
      ]
    }
  ];

  getAll(params: any): Observable<Client[]> {
    if (params.id) {
      return of(
        this.clients.filter(cl => cl.id === +params.id)
      );
    } else {
      return of(this.clients);
    }
  }

  getById(id: number): Observable<Client> {
    return new Observable<Client>((observer) => {
      const client$ = this.clients.find(client => client.id === id);
      if (client$) {
        observer.next(client$);
      } else {
        observer.error(new Error('User not found'));
      }
    });
  }

  save(client: Client): Observable<Client> {
    return of(client).pipe(delay(1000));
  }
}
