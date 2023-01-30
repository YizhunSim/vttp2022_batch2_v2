import { Item, Order } from './../models';
import { Component, Input, OnInit, Output } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cartForm !: FormGroup
  cartArray !: FormArray

  @Output() onNewCheckout = new Subject<Order>()

  @Input() cart: Order | null = null;

  constructor(private fb: FormBuilder){

  }

  ngOnInit(): void {
    this.cartForm =this.createForm(this.cart);
  }

  removeItem(i : number){
    this.cartArray.removeAt(i);
  }

  processForm() : void {
    const cart : Order = this.cartForm.value as Order;
    console.log(">>> cart: ", cart);

    this.onNewCheckout.next(cart); 

    this.cartForm = this.createForm();
  }

  addItem(){
    this.cartArray.push(this.createItem());
  }

  private createItem(item: Item | null = null) : FormGroup {
    return this.fb.group({
      item: this.fb.control(item?.itemName? item.itemName : '', [Validators.required]),
      quantity: this.fb.control(item?.quantity? item.quantity : '', [Validators.required]),
      price: this.fb.control(item?.price? item.price : '', [Validators.required]),
    })
  }

  private createItems(items: Item[] = []) : FormArray{
    // Returns an array of FormGroup
    return this.fb.array(items.map(i => this.createItem(i)));
  } 


  private createForm(cart : Order | null = null): FormGroup {
    this.cartArray = this.createItems(cart?.cartItems? cart.cartItems : []);

    return this.fb.group({
      name: this.fb.control<string>('', [Validators.required, Validators.minLength(3)]),
      address: this.fb.control<string>('', [Validators.required]),
      email: this.fb.control<string>('', [Validators.required, Validators.email]),
      phone: this.fb.control<string>('', [Validators.required]),
      express: this.fb.control<boolean>(false),
      delivery: this.fb.control<string>('', [Validators.required]),
      cartItems: this.cartArray
    })
  }
}
