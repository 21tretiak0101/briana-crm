import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AuthService} from '../auth/auth.service';
import {Employee} from '../../entities';
import {Observable, of} from 'rxjs';
import {delay} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  authenticated: Employee;
  employees: Array<Employee> = [
    {
      id: 9,
      name: 'Stefania Parminter',
      phone: '+2039201155',
      email: 'sparminter3@biblegateway.com',
      address: {
        country: 'Macedonia',
        city: 'Gorno Orizari',
        postcode: '14082'
      },
      positionName: 'Junior Executive',
      imgSrc: 'http://dummyimage.com/103x101.bmp/ff4444/ffffff',
      description: 'Strong risk and issue management skills'
    },
    {
      id: 4,
      name: 'Roxanna Aubert',
      phone: '+1266050214',
      email: 'raubert3@webnode.com',
      address: {
        country: 'France',
        city: 'Laval',
        postcode: '53009'
      },
      positionName: 'Recruiting Manager',
      description: 'May execute multiple projects at one given time'
    }
  ];

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {
    this.authenticated = authService.authenticated;
  }

  update(employee: Employee): Observable<Employee> {
    return of(employee).pipe(delay(1000));
  }

  getById(id: number): Observable<Employee> {
    return of(
      this.employees.find(cl => cl.id === +id)
    );
  }

  updateImage(file: File): Observable<any> {
    return of(file.name).pipe(delay(4000));
  }

  removeImage(): Observable<any> {
    return new Observable<any>(subscriber => {
      subscriber.next('deleted');
    }).pipe(delay(1000));
  }

  getAll(): Observable<Employee[]> {
    return of(this.employees);
  }

  create(employee: Employee): Observable<Employee> {
    return of(employee).pipe(delay(1000));
  }
}
