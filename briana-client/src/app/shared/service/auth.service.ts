import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Employee, Owner} from '../entities';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {BASE_API_URL} from '../../../environments/environment';
import jwtDecode, {JwtPayload} from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private token: string;
  private authenticated: Employee;

  constructor(private http: HttpClient) {}

  register(owner: Owner): Observable<Owner> {
    return this.http.post<Owner>(`${BASE_API_URL}/owner`, owner);
  }

  login(employee: Employee): Observable<any> {
    return this.http.post(`${BASE_API_URL}/auth`, employee)
      .pipe(
        tap((response: any) => {
          localStorage.setItem('auth-token', response.headers['Authorization']);
          this.setToken(this.token);
          this.setAuthenticated();
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
    return !!this.token;
  }

  logout(): void {
    this.setToken(null);
    localStorage.clear();
  }

  getAuthenticated(): Employee {
    return this.authenticated;
  }

  setAuthenticated(): void {
    const payload = jwtDecode<JwtPayload>(this.token);
    const employeeId = payload['employeeId'];
    this.http.get<Employee>(`${BASE_API_URL}/employees/${employeeId}`)
      .subscribe(employee => {
        this.authenticated = employee;
      }
    );
  }
}
