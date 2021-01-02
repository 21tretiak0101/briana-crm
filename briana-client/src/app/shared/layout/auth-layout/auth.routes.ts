import {LoginPageComponent} from '../../../login-page/login-page.component';
import {RegistrationPageComponent} from '../../../registration-page/registration-page.component';
import {Routes} from '@angular/router';

export const AUTH_ROUTES: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'login', component: LoginPageComponent},
  {path: 'registration', component: RegistrationPageComponent}
];
