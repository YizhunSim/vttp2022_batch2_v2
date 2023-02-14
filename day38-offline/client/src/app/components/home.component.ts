import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Task } from '../models';
import { TaskRepository } from '../task.repository';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  form!: FormGroup

  constructor(private fb: FormBuilder, private taskRepo: TaskRepository){

  }

  ngOnInit(): void {
      this.form = this.createForm();
  }

  processTask() : void{
    const task = this.form.value as Task
    console.info('>>> task: ', task)
    task.dueDate = new Date(this.form.get('dueDate')?.value).getTime()
    this.taskRepo.addToDo(task)
    .then(result => {
      this.ngOnInit();
    })
    .catch(error => {
      console.error('>>> error: ', error)
    })
  }

  invalid(){
    // const d = new Date()
    const now = new Date().getTime();
    const dueDate = (new Date(this.form.get('dueDate')?.value)).getTime()
    return this.form.invalid || (dueDate < now)
  }

  private createForm() : FormGroup {
    return this.fb.group({
      task: this.fb.control(''),
      priority: this.fb.control(''),
      dueDate: this.fb.control('')
    })
  }
}
