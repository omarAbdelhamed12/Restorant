import {Component, OnInit} from '@angular/core';
import {CardService} from '../../../service/card.service';


@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})

export class CardComponent  implements OnInit {

  totalPrice = 0;
  totalOrdersSize  = 0;
  constructor(private cardService: CardService) {
  }
    ngOnInit(): void {
      this.cardService.totalPrice.subscribe(
        value => this.totalPrice = value
      );

      this.cardService.totalOrdersSize.subscribe(
        value => this.totalOrdersSize = value
      );
    }

}
