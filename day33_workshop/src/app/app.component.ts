import { OrderComponent } from './components/order.component';
import {Order } from './models';
import { Component, Output, ViewChild } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  order !: Order

  custormerName !: string

  // @ViewChild(OrderComponent)
  // orderComp!: OrderComponent;

  processNewOrder(o : Order){
    console.log('Process cart order');
    console.log('>>>', o);

    this.order = o;
    this.custormerName = o.name;
  }
}
