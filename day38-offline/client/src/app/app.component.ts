import { HttpHeaders, HttpParams } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { firstValueFrom, Subscription } from 'rxjs';
import { Task } from './models';
import { TaskRepository } from './task.repository';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy{
  tasks : Task[] = []
  sub$ !: Subscription
  
  constructor(private taskRepo : TaskRepository){

  }

  ngOnInit() : void {
    this.sub$ = this.taskRepo.onToDo.subscribe(
      () => {
        this.taskRepo.getToDos()
        .then(result => this.tasks = result)
      }
    )

    this.taskRepo.getToDos()
    .then(result => this.tasks = result)
  }

  ngOnDestroy(): void {
      this.sub$.unsubscribe();
  }

  sync() : void {
    this.taskRepo.sync('/api/tasks')
    .then(() => {
      console.info('synced')
    })
  }

  // sync(){
  //   this.taskRepo.synaAsUrlForm('/api/tasks', this.tasks)
  //   .then(() => {
  //     console.info('synced')
  //   })
  // }

}
