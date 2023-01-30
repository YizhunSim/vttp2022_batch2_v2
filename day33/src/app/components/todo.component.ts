import { Task, Todo } from './../models';
import { Component, Input, OnInit, Output } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit{

  @Output() onNewToDo = new Subject<Todo>()

  @Input() todo: Todo | null = null;

  todoForm !: FormGroup
  taskArray !: FormArray

  constructor(private formBuilder: FormBuilder){
    
  }

  ngOnInit(): void {
      this.todoForm =this.createForm(this.todo);
  }

  processForm(){
    const todo :Todo = this.todoForm.value;
    console.log(">>> todo: ", todo);

    this.onNewToDo.next(todo); //emit ***

    this.todoForm = this.createForm();
  }

  addTask(){
    this.taskArray.push(this.createTask())
  }

  removeTask(i : number){
    this.taskArray.removeAt(i);
  }

  abc(): Todo {
    return this.todoForm.value as Todo
  }

  invalid(): boolean{
    return this.todoForm.invalid || this.taskArray.length <= 0;
  }

  clearForm(){
    this.todoForm = this.createForm();
  }

  private createTask(task: Task | null = null) : FormGroup {
    return this.formBuilder.group({
      task: this.formBuilder.control(task?.task? task.task : '', [Validators.required]),
      priority: this.formBuilder.control(''),
    })
  }

  private createTasks(tasks: Task[] = []) : FormArray{
    // Returns an array of FormGroup
    return this.formBuilder.array(tasks.map(t => this.createTask(t)));
  } 

  private createForm(todo : Todo | null = null): FormGroup {
    this.taskArray = this.createTasks(todo?.tasks? todo.tasks : []);

    // if (this.todo?.tasks){
    //   this.taskArray = this.createTasks(this.todo.tasks)
    // } else{
    //   this.taskArray = this.createTasks([]);
    // }

    return this.formBuilder.group({
      name: this.formBuilder.control(todo?.name? todo.name : '', [Validators.required, Validators.minLength(3)]),
      email: this.formBuilder.control(todo?.email? todo.email : '', [Validators.required, Validators.email]),
      tasks: this.taskArray
    })
  }
}
