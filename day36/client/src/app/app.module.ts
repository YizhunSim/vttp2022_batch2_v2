import { PolarBearComponent } from './components/polar-bear.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { DogComponent } from './components/dog.component';
import { HomeComponent } from './components/home.component';
import { CustomerComponent } from './components/customer.component';
import { ReactiveFormsModule } from '@angular/forms';

const appRoutes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  {
    path: 'dogs',
    component: DogComponent,
  },
  {
    path: 'polar',
    component: PolarBearComponent,
  },
  {
    path: 'customer/:custName',
    component: CustomerComponent,
  },
  {
    path: '**',
    redirectTo: '/',
    pathMatch: 'full',
  },
];

@NgModule({
  declarations: [AppComponent, DogComponent, HomeComponent, CustomerComponent],
  imports: [BrowserModule, ReactiveFormsModule, RouterModule.forRoot(appRoutes)],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
