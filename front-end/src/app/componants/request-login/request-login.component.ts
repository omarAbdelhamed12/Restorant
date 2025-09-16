import { Component, OnInit } from '@angular/core';
import { OrderResponseVm } from '../../../model/order-response-vm';
import { OrderService } from '../../../service/order.service';

@Component({
  selector: 'app-request-login',
  templateUrl: './request-login.component.html',
  styleUrls: ['./request-login.component.css']
})
export class RequestLoginComponent implements OnInit {

  orders: OrderResponseVm[] = [];
  loading = true;
  errorMessage = '';
  messageAr = '';
  messageEn = '';

  constructor(private orderService: OrderService) { }

  ngOnInit(): void {
    this.loadOrders();
  }

  loadOrders(): void {
    this.orderService.getOrdersByUser().subscribe({
      next: (data) => {
        this.orders = data;
        this.loading = false;
      },
      error: (err) => {
        this.loading = false; // ✅ هنا تقفل اللودينج
        this.messageAr = err.error.bundleMessage.message_ar ;
        this.messageEn = err.error.bundleMessage.message_en ;
      }
    });
  }

}
