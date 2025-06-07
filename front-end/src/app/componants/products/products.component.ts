import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../../service/product.service';
import {Product} from '../../../model/product';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  products: Product[] = [];
  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(( ) => this.handelAllAction());

  }
  constructor(private  productService: ProductService, private activatedRoute: ActivatedRoute) { }

  // tslint:disable-next-line:typedef
  handelAllAction() {
    const idCategoryExist = this.activatedRoute.snapshot.paramMap.has('id');
    const keyCategoryExist = this.activatedRoute.snapshot.paramMap.has('key');
    if (idCategoryExist){
      const idCategory  = this.activatedRoute.snapshot.paramMap.get('id');
      this.getProductsByCategoryId(idCategory);
    }else if (keyCategoryExist){
      const key  = this.activatedRoute.snapshot.paramMap.get('key');
      this.getProductsByName(key);
    }else {
      this.getAllProducts();
    }
  }


  // tslint:disable-next-line:typedef
  getAllProducts() {
    this.productService.getAllProducts().subscribe(
      response => {
        this.products = response;
      }
    );
  }
// tslint:disable-next-line:typedef
  getProductsByCategoryId(id) {
    this.productService.getProductsByCategoryId(id).subscribe(
      response => {
        this.products = response;
      }
    );
  }
  // tslint:disable-next-line:typedef
  getProductsByName(key) {
    this.productService.getProductsByName(key).subscribe(
      response => {
        this.products = response;
      }
    );
  }
}
