import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {LoginPageComponent} from './login-page/login-page.component';
import {AuthLayoutComponent} from './shared/layout/auth-layout/auth-layout.component';
import {SystemLayoutComponent} from './shared/layout/system-layout/system-layout.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TokenInterceptor} from './shared/interceptor/token.interceptor';
import {DashboardPageComponent} from './dashboard-page/dashboard-page.component';
import {AnalyticsPageComponent} from './analytics-page/analytics-page.component';
import {HistoryPageComponent} from './history-page/history-page.component';
import {HistoryListComponent} from './history-page/history-list/history-list.component';
import {HistoryFilterComponent} from './history-page/history-filter/history-filter.component';
import {SettingsPageComponent} from './settings-page/settings-page.component';
import {ClientsPageComponent} from './clients-page/clients-page.component';
import {EmployeesPageComponent} from './employees-page/employees-page.component';
import {EventsPageComponent} from './events-page/events-page.component';
import {ProfileSettingsComponent} from './settings-page/profile-settings/profile-settings.component';
import {PasswordSettingsComponent} from './settings-page/password-settings/password-settings.component';
import {AvatarSettingsComponent} from './settings-page/avatar-settings/avatar-settings.component';
import {LanguageSettingsComponent} from './settings-page/language-settings/language-settings.component';
import {CircularLoaderComponent} from './shared/component/circular-loader/circular-loader.component';
import {SuccessComponent} from './shared/component/success/success.component';
import {ClientInfoComponent} from './clients-page/client-info/client-info.component';
import {ClientOrdersComponent} from './clients-page/client-info/client-orders/client-orders.component';
import {ClientFilterComponent} from './clients-page/client-filter/client-filter.component';
import {ClientOverviewComponent} from './clients-page/client-info/client-overview/client-overview.component';
import {DefaultClientInfoComponent} from './clients-page/client-info/default-client-info/default-client-info.component';
import {ClientsListComponent} from './clients-page/clients-list/clients-list.component';
import {ClientModalComponent} from './clients-page/client-modal/client-modal.component';
import {LanguagePipe} from './shared/pipe/language/language.pipe';
import {DateTimePipe} from './shared/pipe/date/date-time.pipe';
import {DatePipe} from '@angular/common';
import {EmployeeInfoComponent} from './employees-page/employee-info/employee-info.component';
import {EmployeesListComponent} from './employees-page/employees-list/employees-list.component';
import {AddressPipe} from './shared/pipe/address/address.pipe';
import {EmployeeActivityComponent} from './employees-page/employee-activity/employee-activity.component';
import {EmployeeAnalyticsComponent} from './employees-page/employee-analytics/employee-analytics.component';
import {ProductsPageComponent} from './products-page/products-page.component';
import {ProductInfoComponent} from './products-page/product-info/product-info.component';
import {ProductListComponent} from './products-page/product-list/product-list.component';
import {ProductImageFormComponent} from './products-page/product-info/product-image-form/product-image-form.component';
import {ImageHandlerComponent} from './shared/component/image-handler/image-handler.component';
import {ProductModalComponent} from './products-page/product-modal/product-modal.component';
import {ModalHandlerComponent} from './shared/component/modal-handler/modal-handler.component';
import {DynamicQuestionComponent} from './shared/component/dynamic-question/dynamic-question.component';
import {ProductCreationComponent} from './products-page/product-creation/product-creation.component';
import {ProductImageComponent} from './products-page/product-creation/product-image/product-image.component';
import {GenericImageHandlerComponent} from './shared/component/image-handler/generic-image-handler/generic-image-handler.component';
import {CategoriesPageComponent} from './categories-page/categories-page.component';
import {CategoryCreationComponent} from './categories-page/category-creation/category-creation.component';
import {CategoryListComponent} from './categories-page/category-list/category-list.component';
import {CategoryInfoComponent} from './categories-page/category-info/category-info.component';
import {CategoryProductsComponent} from './categories-page/category-info/category-products/category-products.component';
import {CategoryModalComponent} from './categories-page/category-info/category-modal/category-modal.component';
import {EmittingImageHandlerComponent} from './shared/component/image-handler/emitting-image-handler/emitting-image-handler.component';
import {ResponsiveCircularLoaderComponent} from './shared/component/circular-loader/responsive-circular-loader/responsive-circular-loader.component';
import {ClientCreationComponent} from './clients-page/client-creation/client-creation.component';
import {PositionCreationComponent} from './employees-page/position-creation/position-creation.component';
import {EmployeeCreationComponent} from './employees-page/employee-creation/employee-creation.component';
import {CreationHandlerComponent} from './shared/component/creation-handler/creation-handler.component';
import {TotalPricePipe} from './shared/pipe/total-price/total-price.pipe';

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    AuthLayoutComponent,
    SystemLayoutComponent,
    DashboardPageComponent,
    AnalyticsPageComponent,
    HistoryPageComponent,
    ProductsPageComponent,
    HistoryListComponent,
    HistoryFilterComponent,
    SettingsPageComponent,
    ClientsPageComponent,
    EmployeesPageComponent,
    EventsPageComponent,
    ProfileSettingsComponent,
    PasswordSettingsComponent,
    AvatarSettingsComponent,
    LanguageSettingsComponent,
    CircularLoaderComponent,
    SuccessComponent,
    ClientInfoComponent,
    ClientOrdersComponent,
    ClientFilterComponent,
    ClientOverviewComponent,
    DefaultClientInfoComponent,
    ClientsListComponent,
    ClientModalComponent,
    LanguagePipe,
    DateTimePipe,
    EmployeeInfoComponent,
    EmployeesListComponent,
    AddressPipe,
    EmployeeActivityComponent,
    EmployeeAnalyticsComponent,
    ProductsPageComponent,
    ProductInfoComponent,
    ProductListComponent,
    ProductImageFormComponent,
    ImageHandlerComponent,
    ProductModalComponent,
    CategoryInfoComponent,
    DynamicQuestionComponent,
    ProductCreationComponent,
    ProductImageComponent,
    GenericImageHandlerComponent,
    CategoriesPageComponent,
    CategoryCreationComponent,
    CategoryListComponent,
    CategoryProductsComponent,
    CategoryModalComponent,
    ModalHandlerComponent,
    EmittingImageHandlerComponent,
    ResponsiveCircularLoaderComponent,
    ClientCreationComponent,
    PositionCreationComponent,
    EmployeeCreationComponent,
    CreationHandlerComponent,
    TotalPricePipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      multi: true,
      useClass: TokenInterceptor
    },
    DatePipe,
    LanguagePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
