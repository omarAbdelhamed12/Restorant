import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductDetailsService {
  private baseUrl = 'http://localhost:7070/ProductDetails';

  constructor(private http: HttpClient) { }

  // فنكشن لإضافة تفاصيل المنتج
  addProductDetails(productDetails: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/addDetail`, productDetails);
  }
  getProductDetailsById(productId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${productId}`);
  }

}
