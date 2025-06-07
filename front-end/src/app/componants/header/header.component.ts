import { Component } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
constructor(private router: Router) {
}
  // tslint:disable-next-line:typedef
  search(key) {
  this.router.navigateByUrl('/search/' + key);
  }
}
