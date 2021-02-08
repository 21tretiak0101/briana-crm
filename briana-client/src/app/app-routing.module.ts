import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {ROUTES} from './shared/layout/system-layout/routes';

@NgModule({
  imports: [RouterModule.forRoot(ROUTES)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
