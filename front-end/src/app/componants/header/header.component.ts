import { Component } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
constructor(private router: Router, private activatedRoute: ActivatedRoute) {
}
  // tslint:disable-next-line:typedef
  search(key) {
    const idCategoryExist = this.activatedRoute.snapshot.paramMap.has('id');
    if (idCategoryExist) {
      const idCategory = this.activatedRoute.snapshot.paramMap.get('id');
      this.router.navigateByUrl('/category/' + idCategory + '/search/' + key);
      return;
    }
    this.router.navigateByUrl('/search/' + key);
  }
}
