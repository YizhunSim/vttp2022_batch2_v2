import { Subscription } from 'rxjs';
import { FormComponent } from './components/form.component';
import { AfterViewInit, Component, ViewChild, OnDestroy } from '@angular/core';
import { User } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit, OnDestroy{
  @ViewChild(FormComponent)
  formComp !: FormComponent;

  friends: User[] = [];

  name : string = "";

  sub$!: Subscription

  ngAfterViewInit(): void {
    this.sub$ = this.formComp.onNewUser.subscribe(
      (data: User) => {
        this.onNewUser(data)
      }
    )
  }

  ngOnDestroy(): void {
      this.sub$.unsubscribe();
  }
  
  
  onNewUser(data: User){
    const tmp: User[] = []

    // for (let f of this.friends){
    //   tmp.push(f)
    // }
    // tmp.push(data)
    // this.friends = tmp;
    // shallow copy

    this.friends = [...this.friends, data];

    // Reference type
    // this.friends.push(data);

    // scalar/primitive type
    this.name = data.name;
  }
}


// Proper binding
// Service
// View Child