import {DashboardPageComponent} from '../../../dashboard-page/dashboard-page.component';
import {AnalyticsPageComponent} from '../../../analytics-page/analytics-page.component';
import {EventsPageComponent} from '../../../events-page/events-page.component';
import {HistoryPageComponent} from '../../../history-page/history-page.component';
import {EmployeesPageComponent} from '../../../employees-page/employees-page.component';
import {ClientsPageComponent} from '../../../clients-page/clients-page.component';
import {ClientInfoComponent} from '../../../clients-page/client-info/client-info.component';
import {SettingsPageComponent} from '../../../settings-page/settings-page.component';
import {AppRoutes} from './routes';
import {DefaultClientInfoComponent} from '../../../clients-page/client-info/default-client-info/default-client-info.component';
import {EmployeeInfoComponent} from '../../../employees-page/employee-info/employee-info.component';
import {ProductsPageComponent} from '../../../products-page/products-page.component';
import {ProductInfoComponent} from '../../../products-page/product-info/product-info.component';
import {ProductCreationComponent} from '../../../products-page/product-creation/product-creation.component';
import {CategoriesPageComponent} from '../../../categories-page/categories-page.component';
import {CategoryInfoComponent} from '../../../categories-page/category-info/category-info.component';
import {CategoryCreationComponent} from '../../../categories-page/category-creation/category-creation.component';
import {ClientCreationComponent} from '../../../clients-page/client-creation/client-creation.component';
import {PositionCreationComponent} from '../../../employees-page/position-creation/position-creation.component';
import {EmployeeCreationComponent} from '../../../employees-page/employee-creation/employee-creation.component';

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
    id: 'pr',
    path: 'products',
    component: ProductsPageComponent,
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
        path: 'new',
        component: ClientCreationComponent
      },
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
  {path: 'categories', component: CategoriesPageComponent},
  {path: 'categories/new', component: CategoryCreationComponent},
  {path: 'categories/:id', component: CategoryInfoComponent},
  {path: 'products/new', component: ProductCreationComponent},
  {path: 'products/:id', component: ProductInfoComponent},
  {path: 'employees/new', component: EmployeeCreationComponent},
  {path: 'employees/:id', component: EmployeeInfoComponent},
  {path: 'positions/new', component: PositionCreationComponent}
];
