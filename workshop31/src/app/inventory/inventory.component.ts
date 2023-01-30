import { Item } from '../models/Item';
import { Component, Input } from '@angular/core';
import { ShoppingCartService } from '../shopping-cart.service';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css'],
})
export class InventoryComponent {
  inventories: Item[] = [];

  constructor(private shoppingCartService : ShoppingCartService){
    this.inventories = this.shoppingCartService.getInventories();
  }

  onAdd(item: Item){
    this.shoppingCartService.shoppingCartUpdate.emit(item); //  this.onNewToDo.next(todo); //emit
    // this.inventories = this.shoppingCartService.onAdd(item);
  }


}
