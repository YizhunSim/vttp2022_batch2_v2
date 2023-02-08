import { BggService } from './bgg-service';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { GamesComponent } from './components/games.component';
import { CommentsComponent } from './components/comments.component';
import { RouterModule, Routes } from '@angular/router';

const appRoutes: Routes = [
  { path: '', component: GamesComponent },
  { path: 'games', component: GamesComponent },
  { path: 'game/:gameId/comments', component: CommentsComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full', },
];

@NgModule({
  declarations: [AppComponent, GamesComponent, CommentsComponent],
  imports: [BrowserModule, HttpClientModule, RouterModule.forRoot(appRoutes, {useHash: true})],
  providers: [BggService],
  bootstrap: [AppComponent],
})
export class AppModule {}
