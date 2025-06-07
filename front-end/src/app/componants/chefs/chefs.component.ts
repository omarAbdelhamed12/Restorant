import { Component, OnInit } from '@angular/core';
import {Chef} from '../../../model/chef';
import {ChefService} from '../../../service/chef.service';

@Component({
  selector: 'app-chefs',
  templateUrl: './chefs.component.html',
  styleUrls: ['./chefs.component.css']
})
export class ChefsComponent implements OnInit {
  chefs: Chef[] = [];
  constructor(private chefService: ChefService) { }

  ngOnInit(): void {
    this.getAllChefs();
  }
  // tslint:disable-next-line:typedef
  getAllChefs() {
    this.chefService.getAllChefs().subscribe(
      response => {
        this.chefs = response;
      }
    );
  }

  protected readonly Chef = Chef;
}
