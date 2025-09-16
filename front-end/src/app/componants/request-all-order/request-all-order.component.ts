import { Component, OnInit } from '@angular/core';
import { OrderResponseVm } from '../../../model/order-response-vm';
import { OrderService } from '../../../service/order.service';

@Component({
  selector: 'app-request-all-order',
  templateUrl: './request-all-order.component.html',
  styleUrls: ['./request-all-order.component.css']
})
export class RequestAllOrderComponent implements OnInit {

  orders: OrderResponseVm[] = [];
  loading = true;
  messageAr = '';
  messageEn = '';

  constructor(private orderService: OrderService) {}

  ngOnInit(): void {
    this.loadAllOrders();
  }

  loadAllOrders(): void {
    this.orderService.getAllOrders().subscribe({
      next: (data) => {
        this.orders = data;
        this.loading = false;
      },
      error: (err) => {
        this.loading = false;
        this.messageAr = err.error.bundleMessage.message_ar  ;
        this.messageEn = err.error.bundleMessage.message_en ;
      }
    });
  }
}
