import { ShoppingCartSelection } from './../models/shopping-cart-selection';
import { ShoppingCartService } from './../shopping-cart.service';
import { Item } from './../models/Item';
import { Component} from '@angular/core';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css'],
})
export class ShoppingCartComponent {
  cart: ShoppingCartSelection[] = []

  constructor(private shoppingCartService: ShoppingCartService) {
    this.shoppingCartService.shoppingCartUpdate.subscribe((item: Item) => {
      alert('New Item Added: ' + item.name);
      this.shoppingCartService.onAdd(item);

      this.cart = this.shoppingCartService.getCart();
    });
  }

  onRemove(idx: number) : void{
    this.shoppingCartService.onRemoveCartItem(idx);
  }
}
