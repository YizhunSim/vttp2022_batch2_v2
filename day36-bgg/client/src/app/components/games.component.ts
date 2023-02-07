import { BggService } from './../bgg-service';
import { Component, OnInit } from '@angular/core';
import { Game } from '../models';

@Component({
  selector: 'app-games',
  templateUrl: './games.component.html',
  styleUrls: ['./games.component.css']
})
export class GamesComponent implements OnInit{
  games: Game[] = []

  constructor(private bggSvc: BggService){
    
  }

  ngOnInit(): void {
    this.bggSvc.getGames()
    .then(result => {
      this.games = result
      console.info(">>> game: ", result)
    })
    .catch(error => {
      console.error(">>> error: ", error)
    })
      
  }
}
