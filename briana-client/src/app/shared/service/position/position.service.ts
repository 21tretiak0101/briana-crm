import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {Position} from '../../entities';
import {delay} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PositionService {
  create(position: Position): Observable<Position> {
    return of(position).pipe(delay(1000));
  }

  getAllPermissions(): Observable<string[]> {
    return of(['product:write', 'employee:read']);
  }

  getAll(): Observable<Position[]> {
    return of([{id: '1', name: 'Developer'}, {id: '2', name: 'Manager'}]);
  }
}
