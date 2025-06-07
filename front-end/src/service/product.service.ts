import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {Product} from '../model/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  baseUrl = 'http://localhost:7070/api/product';
  constructor(private http: HttpClient) { }
  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.baseUrl + '/getAll').pipe(
      map(
        response => response
      )
    );
  }
  getProductsByCategoryId(id): Observable<Product[]> {
    return this.http.get<Product[]>(this.baseUrl + '/allProductByCategoryId/' + id).pipe(
      map(
        response => response
      )
    );
  }
  getProductsByName(key): Observable<Product[]> {
    return this.http.get<Product[]>(this.baseUrl + '/productSearch?productSearch=' + key).pipe(
      map(
        response => response
      )
    );
  }
}
