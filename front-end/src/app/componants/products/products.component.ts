import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../../service/product.service';
import {Product} from '../../../model/product';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  products: Product[] = [];
  constructor(private  productService: ProductService) { }

  ngOnInit(): void {
    this.getAllProducts();
  }
  // tslint:disable-next-line:typedef
  getAllProducts() {
    this.productService.getAllProducts().subscribe(
      response => {
        this.products = response;
      }
    );
  }

}
