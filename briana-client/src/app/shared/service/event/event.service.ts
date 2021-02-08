import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {Event, EventType} from '../../entities';

@Injectable({
  providedIn: 'root'
})
export class EventService {
  events: Event[] = [
    {
      type: EventType.ADD,
      message: 'product:10',
      published: new Date(),
      publisherId: 20,
      publisherName: 'Bob'
    },
    {
      type: EventType.UPDATE,
      message: 'client:12',
      published: new Date(),
      publisherId: 232,
      publisherName: 'Julia'
    }
  ];

  getAll(): Observable<Event[]> {
    return of(this.events);
  }
}
