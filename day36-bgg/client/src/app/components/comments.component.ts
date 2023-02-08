import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { BggService } from '../bgg-service';
import { Comment } from '../models';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {
  params$!: Subscription
  comments !: Comment[];
  
  constructor(private activatedRoute: ActivatedRoute, private bggSvc: BggService){

  }

  ngOnInit(): void {
    this.params$ = this.activatedRoute.params.subscribe(
      (params) => {
        const gameId = params['gameId']
        this.bggSvc.getGameCommentsById(gameId)
        .then(result => {
          this.comments = result;
          console.info(`>>> game ${gameId}'s comments: `, this.comments)
        })
        .catch(error => {
          console.error(">>> error: ", error)
        })
      }

    )
  }
}
