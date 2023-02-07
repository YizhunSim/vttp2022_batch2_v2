import { BGGService } from './bgg-service';
import { DisplayComponent } from './components/display.component';
import { SearchComponent } from './components/search.component';
import { AfterViewInit, Component, OnDestroy, ViewChild, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements AfterViewInit, OnDestroy, OnInit{
  searchTerms :string = "";
  sub$!: Subscription

  constructor(private bggSvc: BGGService){

  }

  onResults(games: Game[]) {

  }

  ngOnInit(): void {
      this.sub$ = this.bggSvc.onSearchQueries.subscribe(
        (name: string) => {
          this.searchTerms = name;
        })
  }
  
  ngOnDestroy(): void {
    this.sub$.unsubscribe();
  }
  
  ngAfterViewInit(): void {

  }

}
