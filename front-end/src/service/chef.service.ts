import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Chef} from '../model/chef';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ChefService {
  url = 'http://localhost:7070/api/chef/getAllChef';
  constructor(private http: HttpClient) {}
  getAllChefs(): Observable<Chef[]> {
    return this.http.get<Chef[]>(this.url).pipe(
      map(
        response => response
      )
    );
  }
}
