import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { BGGService } from '../bgg-service';

@Component({
  selector: 'app-display',
  templateUrl: './display.component.html',
  styleUrls: ['./display.component.css']
})
export class DisplayComponent implements OnInit, OnDestroy{
  sub$!: Subscription

  @Input()
  games: Game[] = []

  constructor(private bggSvc: BGGService){

  }

  ngOnInit(): void {
      this.sub$ = this.bggSvc.onSearchResults.subscribe(
        (games) => {
          this.games = games;
        }
      )
  }
  

  ngOnDestroy(): void {
      this.sub$.unsubscribe();
  }
}
