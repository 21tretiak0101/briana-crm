import {LoginPageComponent} from '../../../login-page/login-page.component';
import {Routes} from '@angular/router';

export const AUTH_ROUTES: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'login', component: LoginPageComponent}
];
