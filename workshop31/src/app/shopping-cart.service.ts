import { ShoppingCartSelection } from './models/shopping-cart-selection';
import { EventEmitter } from '@angular/core';
import { Item } from './models/Item';

export class ShoppingCartService {
  inventories: Item[] = [
    { name: 'Acron Squash', imageUrl: 'acorn_squash' },
    { name: 'Bell Pepper', imageUrl: 'bell_pepper' },
    { name: 'Carrot', imageUrl: 'carrot' },
    { name: 'Eggplant', imageUrl: 'eggplant' },
    { name: 'Mushroom', imageUrl: 'mushroom' },
    { name: 'Tomato', imageUrl: 'tomato' },
    { name: 'Zucchini', imageUrl: 'zucchini' },
  ];

  shoppingCart: ShoppingCartSelection[] = [];

  shoppingCartUpdate = new EventEmitter<Item>();

  onAdd(item: Item): void {
    const newCartItem = new ShoppingCartSelection(item.name, item.imageUrl, 1);

    const checkItemExistFunction = (obj: { name: string; }) => obj.name === item.name;

    if (this.shoppingCart.some(checkItemExistFunction)){
        const updatedItemIdx = this.shoppingCart.findIndex((obj => obj.name == item.name));
        this.shoppingCart[updatedItemIdx].quantity++;
        console.log(`Updated Item Name: ${this.shoppingCart[updatedItemIdx].name} Item Quantity: ${this.shoppingCart[updatedItemIdx].quantity}`);
    } else{
        console.log(`Item Does not Exists`);
        this.shoppingCart.push(newCartItem);
    }
  }

  getInventories() {
    return this.inventories;
  }

  getCart(){
    return this.shoppingCart;
  }

  onRemoveCartItem(idxToBeRemove : number) : void{
    const itemToBeRemoved = this.shoppingCart[idxToBeRemove];
    console.log(`Item Removed!: ${itemToBeRemoved.name}`);

    this.shoppingCart.splice(idxToBeRemove, 1);
  }

}
