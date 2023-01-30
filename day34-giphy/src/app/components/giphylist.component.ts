import { GiphService } from './../giph.service';
import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Gif } from '../models';

@Component({
  selector: 'app-giphylist',
  templateUrl: './giphylist.component.html',
  styleUrls: ['./giphylist.component.css']
})
export class GiphylistComponent {
  @Input() 
  gifs !: Gif[]

}
