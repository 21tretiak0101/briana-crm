import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Employee, Owner} from '../../entities';
import {BehaviorSubject, Observable} from 'rxjs';
import {delay, tap} from 'rxjs/operators';
import jwtDecode, {JwtPayload} from 'jwt-decode';

const BASE_API_URL = 'api';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  authenticated: Employee = {
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
  };
  private token: string;
  private subject = new BehaviorSubject(this.authenticated);

  constructor(private http: HttpClient) { }

  get authenticatedSub(): Observable<Employee> {
    return this.subject.asObservable().pipe(delay(10));
  }

  register(owner: Owner): Observable<Owner> {
    return this.http.post<Owner>(`${BASE_API_URL}/owner`, owner);
  }

  login(employee: Employee): Observable<any> {
    return this.http.post(`${BASE_API_URL}/auth`, employee)
      .pipe(
        tap((response: any) => {
          localStorage.setItem('auth-token', response.headers['Authorization']);
          this.setToken(this.token);
          this.authenticate();
        })
      );
  }

  setToken(value: string) {
    this.token = value;
  }

  getToken(): string {
    return this.token;
  }

  isAuthenticated(): boolean {
    return true;
  }

  logout(): void {
    this.setToken(null);
    localStorage.clear();
  }

  authenticate(): void {
    const payload = jwtDecode<JwtPayload>(this.token);
    const employeeId = payload['employeeId'];
    this.http.get<Employee>(`${BASE_API_URL}/employees/${employeeId}`)
      .subscribe(employee => {
        this.authenticated = employee;
      }
    );
  }

  update(updated: Employee): void {
    this.authenticated = {...updated};
    this.subject.next(this.authenticated);
  }

  updateImage(image: File) {
    const reader = new FileReader();
    reader.onload = () => {
      this.authenticated.imgSrc = reader.result as string;
      this.subject.next(this.authenticated);
    };
    reader.readAsDataURL(image);
  }

  removeImage(): void {
    this.authenticated.imgSrc = '';
    this.subject.next(this.authenticated);
  }
}
