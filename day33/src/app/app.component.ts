import { TodoComponent } from './components/todo.component';
import { Todo } from './models';
import { Component, ViewChild, OnInit, AfterViewInit, ElementRef } from '@angular/core';

const TODO: Todo = {
  name: 'barney',
  email: 'barney@gmail.com',
  tasks: [
    { task: 'jogging', priority: 'high'},
    { task: 'lunch with friends', priority: 'high'}
  ]
  
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, AfterViewInit{

  @ViewChild(TodoComponent)
  todoComp!: TodoComponent;

  @ViewChild('clearBtn')
  clearBtn!: ElementRef;

  data: Todo = TODO;
  
  ngOnInit(): void {
      console.info('onInit: ', this.todoComp);
      console.info('onInit clearBtn: ', this.todoComp);
  }

  ngAfterViewInit(): void {
      console.info('afterViewInit: ', this.todoComp);
      console.info('afterViewInit clearBtn: ', this.todoComp);
  }

  newToDo(todo : Todo){
    console.log('Process to do');
    console.log('>>>', todo);
  }

  processToDo(){
    this.data = this.todoComp.abc();
    this.todoComp.clearForm();
    console.info('In process to do', this.data);
    this.clearBtn.nativeElement.innerText = `${this.data.name} - ${this.data.email}`;
    this.clearBtn.nativeElement.disabled = true;
  }



}
