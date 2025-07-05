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
  pageNumber = 1;
  pageSize = 6;
  collectionSize: number;
  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(( ) => this.handelAllAction(this.pageNumber));

  }
  constructor(private  productService: ProductService, private activatedRoute: ActivatedRoute) { }

  // tslint:disable-next-line:typedef
  handelAllAction(pageNumber) {
    const idCategoryExist = this.activatedRoute.snapshot.paramMap.has('id');
    const keyCategoryExist = this.activatedRoute.snapshot.paramMap.has('key');

    if (idCategoryExist && keyCategoryExist) {
      const idCategory  = this.activatedRoute.snapshot.paramMap.get('id');
      const key  = this.activatedRoute.snapshot.paramMap.get('key');
      this.searchByCategoryIdAndKey(idCategory , key , pageNumber);
      return;
}

    if (idCategoryExist){
      const idCategory  = this.activatedRoute.snapshot.paramMap.get('id');
      this.getProductsByCategoryId(idCategory, pageNumber  );
    }else if (keyCategoryExist){
      const key  = this.activatedRoute.snapshot.paramMap.get('key');
      this.getProductsByName(key, pageNumber);
    }else {
      this.getAllProducts(pageNumber);
    }
  }


  // tslint:disable-next-line:typedef
  getAllProducts(pageNumber) {
    this.productService.getAllProducts(pageNumber , this.pageSize).subscribe(
      response => {
        // @ts-ignore
        this.products = response.products;
        // @ts-ignore
        this.collectionSize = response.size;
      }
    );
  }
// tslint:disable-next-line:typedef
  getProductsByCategoryId(id , pageNumber ) {
    this.productService.getProductsByCategoryId(id , pageNumber , this.pageSize ).subscribe(
      response => {
        // @ts-ignore
        this.products = response.products;
        // @ts-ignore
        this.collectionSize = response.size;
      }
    );
  }


  // tslint:disable-next-line:typedef
  searchByCategoryIdAndKey(id , key , pageNumber ) {
    this.productService.searchByCategoryIdAndKey(id , key , pageNumber, this.pageSize ).subscribe(
      response => {
        // @ts-ignore
        this.products = response.products;
        // @ts-ignore
        this.collectionSize = response.size;
      }
    );
  }

  // tslint:disable-next-line:typedef
  getProductsByName(key , pageNumber ) {
    this.productService.getProductsByName(key , pageNumber , this.pageSize).subscribe(
      response => {
        // @ts-ignore
        this.products = response.products;
        // @ts-ignore
        this.collectionSize = response.size;
      }
    );
  }

  // tslint:disable-next-line:typedef
  doPagination(){
    this.handelAllAction(this.pageNumber);
  }

  // tslint:disable-next-line:typedef
  changePageSize(event: Event){
   this.pageSize = +(event.target as HTMLInputElement).value;
   this.handelAllAction(this.pageNumber);
  }
}
