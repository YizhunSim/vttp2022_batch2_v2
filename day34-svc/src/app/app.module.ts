import { FriendsService } from './components/friends.service';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { FormComponent } from './components/form.component';
import { ListComponent } from './components/list.component';

@NgModule({
  declarations: [
    AppComponent,
    FormComponent,
    ListComponent
  ],
  imports: [
    BrowserModule, ReactiveFormsModule
  ],
  providers: [FriendsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
