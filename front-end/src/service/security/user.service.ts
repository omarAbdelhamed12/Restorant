import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  baseUrl = 'http://localhost:7070/Auth/register';
  baseUrl1 = 'http://localhost:7070/Auth/login';
  constructor(private http: HttpClient) { }
  creatUser(userName, password , address, age, phoneNumber): Observable<any> {
    return this.http.post<any>(this.baseUrl, { userName, password, userDetails: { address, age, phoneNumber}}).pipe(
      map(
        response => response
      )
    );
  }

  login(userName, password  ): Observable<any> {
    return this.http.post<any>(this.baseUrl1, { userName, password }).pipe(
      map(
        response => response
      )
    );
  }

  // tslint:disable-next-line:typedef
  isUserLogin() {
    return sessionStorage.getItem('token') !== null &&
      sessionStorage.getItem('token') !== undefined ;
  }
  // tslint:disable-next-line:typedef
  logout() {
    sessionStorage.removeItem('token');
  }
}
