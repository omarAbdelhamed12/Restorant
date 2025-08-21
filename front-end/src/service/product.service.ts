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
  getAllProducts(pageNumber, pageSize): Observable<any> {
    return this.http.get<Product[]>(`${this.baseUrl}/getAll?pageNumber=${pageNumber}&pageSize=${pageSize}`).pipe(
    // return this.http.get<Product[]>(this.baseUrl + '/getAll' ).pipe(
      map(
        response => response
      )
    );
  }
  getProductsByCategoryId(id, pageNumber, pageSize): Observable<any> {
    // tslint:disable-next-line:max-line-length
    return this.http.get<Product[]>(`${this.baseUrl}/allProductByCategoryId/${id}?pageNumber=${pageNumber}&pageSize=${pageSize}`).pipe(
    // return this.http.get<Product[]>(this.baseUrl + '/allProductByCategoryId/' + id).pipe(
      map(
        response => response
      )
    );
  }
  getProductsByName(key, pageNumber, pageSize): Observable<any> {
    // tslint:disable-next-line:max-line-length
    return this.http.get<Product[]>(`${this.baseUrl}/productSearch?productSearch=${key}&pageNumber=${pageNumber}&pageSize=${pageSize}`).pipe(

    // return this.http.get<Product[]>(this.baseUrl + '/productSearch?productSearch=' + key).pipe(
      map(
        response => response
      )
    );
  }

  searchByCategoryIdAndKey(id, key, pageNumber, pageSize ): Observable<any> {
    // tslint:disable-next-line:max-line-length
    return this.http.get<Product[]>(`${this.baseUrl}/categorySearch?categoryId=${id}&searchValue=${key}&pageNumber=${pageNumber}&pageSize=${pageSize}`).pipe(

      // return this.http.get<Product[]>(this.baseUrl + '/productSearch?productSearch=' + key).pipe(
      map(
        response => response
      )
    );
  }


}
