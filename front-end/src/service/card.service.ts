// @ts-ignore

import { Injectable } from '@angular/core';
import {CardOrder} from '../model/card-order';
import {BehaviorSubject, Observable, Subject} from 'rxjs';
import {HttpClient} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class CardService {

  orders: CardOrder[] = [];
  totalPrice: Subject<number> = new BehaviorSubject<number>(0);
  totalOrdersSize: Subject<number> = new BehaviorSubject<number>(0);
  constructor() {
  }

  addProductOrder = (order: CardOrder) => {
    let isExist = false;
    let existedOrder: CardOrder;
    existedOrder = undefined;

    if (this.orders.length > 0) {
      existedOrder = this.orders.find(orderCard => orderCard.id === order.id);
    }
    isExist = (existedOrder !== undefined);
    if (isExist) {
      existedOrder.quantity++;
    } else {
      this.orders.push(order);
    }
    this.calculateTotalPrice();
  }
  // tslint:disable-next-line:typedef
  calculateTotalPrice()  {
    let totalElementSize = 0;
    let totalElementPrice = 0;

    for (const temp of this.orders) {
      totalElementSize += temp.quantity;
      totalElementPrice += temp.quantity * temp.price;
    }
    this.totalPrice.next(totalElementPrice);
    this.totalOrdersSize.next(totalElementSize);
  }

  decreaseOrder = (order: CardOrder) => {
    order.quantity--;
    if (order.quantity === 0) {
      this.remove(order);
    }
    this.calculateTotalPrice();  // ✅ تحديث بعد النقصان
  }

  // tslint:disable-next-line:typedef
  remove(order: CardOrder) {
    const index = this.orders.findIndex(orderCard => orderCard.id === order.id);
    if (index > -1) {
      this.orders.splice(index, 1);
    }
    this.calculateTotalPrice();

  }
  // tslint:disable-next-line:typedef
  clear() {
    this.orders = [];
    this.calculateTotalPrice();
  }

}
