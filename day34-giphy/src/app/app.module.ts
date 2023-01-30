import { GiphService } from './giph.service';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { GiphylistComponent } from './components/giphylist.component';
import { GiphyformComponent } from './components/giphyform.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    GiphylistComponent,
    GiphyformComponent,
  ],
  imports: [
    BrowserModule, ReactiveFormsModule, HttpClientModule
  ],
  providers: [GiphService],
  bootstrap: [AppComponent]
})
export class AppModule { }
