import {DashboardPageComponent} from '../../../dashboard-page/dashboard-page.component';
import {AnalyticsPageComponent} from '../../../analytics-page/analytics-page.component';
import {EventsPageComponent} from '../../../events-page/events-page.component';
import {HistoryPageComponent} from '../../../history-page/history-page.component';
import {CategoryPageComponent} from '../../../category-page/category-page.component';
import {EmployeesPageComponent} from '../../../employees-page/employees-page.component';
import {ClientsPageComponent} from '../../../clients-page/clients-page.component';
import {ClientInfoComponent} from '../../../clients-page/client-info/client-info.component';
import {SettingsPageComponent} from '../../../settings-page/settings-page.component';
import {CategoryFormComponent} from '../../../category-page/category-form/category-form.component';
import {AppRoutes} from './routes';
import {DefaultClientInfoComponent} from '../../../clients-page/client-info/default-client-info/default-client-info.component';
import {EmployeeInfoComponent} from '../../../employees-page/employee-info/employee-info.component';

export const SYSTEM_ROUTES: AppRoutes = [
  {
    id: 'ds',
    path: 'dashboard',
    component: DashboardPageComponent,
    icon: 'home'
  },
  {
    id: 'an',
    path: 'analytics',
    component: AnalyticsPageComponent,
    icon: 'analytics'
  },
  {
    id: 'ev',
    path: 'events',
    component: EventsPageComponent,
    icon: 'notifications'
  },
  {
    id: 'hs',
    path: 'history',
    component: HistoryPageComponent,
    icon: 'history'
  },
  {
    id: 'ct',
    path: 'categories',
    component: CategoryPageComponent,
    icon: 'shopping_cart'
  },
  {
    id: 'em',
    path: 'employees',
    component: EmployeesPageComponent,
    icon: 'group_work',
  },
  {
    id: 'cl',
    path: 'clients',
    component: ClientsPageComponent,
    icon: 'groups',
    children: [
      {
        path: ':id',
        component: ClientInfoComponent
      },
      {
        path: '',
        component: DefaultClientInfoComponent
      }
    ]
  },
  {
    id: 'st',
    path: 'settings',
    component: SettingsPageComponent,
    icon: 'settings'
  },
  {path: 'category/:id', component: CategoryFormComponent},
  {path: 'employees/:id', component: EmployeeInfoComponent}
];
