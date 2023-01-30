import { FriendsService } from './friends.service';
import { Component, Input, OnChanges, SimpleChanges, OnInit, OnDestroy } from '@angular/core';
import { User } from '../models';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnChanges, OnInit, OnDestroy{
  @Input()
  friends: User[] = []

  @Input()
  name: string = "not set"
  
  count = 0;

  friend$!: Subscription

  constructor(private friendsService : FriendsService){

  }
  
  ngOnInit(): void {
      this.friend$ = this.friendsService.friends.subscribe(
        (data: User) => {
          console.info('>>>> from friendsService: ', data);
        }
      );
  }

  ngOnDestroy(): void {
      this.friend$.unsubscribe()
  }


  ngOnChanges(changes: SimpleChanges): void {
      console.info('------ onChanges')
      console.info('>>>> ', changes);
      this.count = changes['friends'].currentValue.length;
  }

}
