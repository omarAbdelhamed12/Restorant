import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {OrderResponseVm} from '../model/order-response-vm';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private baseUrl = 'http://localhost:7070/order'; // غير البورت لو عندك مختلف

  constructor(private http: HttpClient) {}

  requestOrder(productsIds: number[]): Observable<OrderResponseVm> {
    const token = sessionStorage.getItem('token'); // انت بتخزن التوكن في الـ sessionStorage
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });

    const orderRequest = {
      productsIds
    };

    return this.http.post<OrderResponseVm>(`${this.baseUrl}/request`, orderRequest, { headers });
  }
}
