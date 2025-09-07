import {Component, OnInit} from '@angular/core';
import {CardOrder} from '../../../model/card-order';
import {CardService} from '../../../service/card.service';
import {OrderService} from '../../../service/order.service';
import {OrderResponseVm} from '../../../model/order-response-vm';
import {Router} from '@angular/router';

@Component({
  selector: 'app-card-details',
  templateUrl: './card-details.component.html',
  styleUrls: ['./card-details.component.css']
})
export class CardDetailsComponent implements OnInit {

  orders: CardOrder[] = [];
  receipt: OrderResponseVm | null = null;
  orderSubmitted = false;
  constructor(private cardService: CardService , private orderService: OrderService , private router: Router) {
  }
  ngOnInit(): void {
    this.orders = this.cardService.orders;
  }

  addOrder = (order: CardOrder) => {
    this.cardService.addProductOrder(order);
  }
  decreaseOrder = (order: CardOrder) => {
    this.cardService.decreaseOrder(order);
  }
  remove = (order: CardOrder) => {
    this.cardService.remove(order);
  }

  // tslint:disable-next-line:typedef
  takeOrder() {
    if (this.orders.length === 0) {
      console.error('Cart is empty, can\'t create order');
      return;
    }

    const productIds = this.orders.map(o => o.id);

    this.orderService.requestOrder(productIds).subscribe({
      next: (res) => {
        console.log('Order submitted successfully:', res);
        this.receipt = res;         // ✅ خزّن الفاتورة
        this.cardService.clear();   // ✅ فضي الكارت
        this.orderSubmitted = true;
      },
      error: (err) => {
        console.error('Error submitting order:', err);
      }
    });
  }
// tslint:disable-next-line:typedef
  goHome() {
    this.router.navigateByUrl('products') ;
  }
}
