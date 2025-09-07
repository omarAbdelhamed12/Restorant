import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../../service/product.service';
import {Product} from '../../../model/product';
import {ActivatedRoute} from '@angular/router';
import {CardService} from '../../../service/card.service';
import {CardOrder} from '../../../model/card-order';
import {UserService} from '../../../service/security/user.service';
import { trigger, style, animate, transition } from '@angular/animations';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css'],
  animations: [
    trigger('fadeIn', [
      transition(':enter', [
        style({ opacity: 0, transform: 'translateY(20px)' }),
        animate('500ms ease-out', style({ opacity: 1, transform: 'translateY(0)' }))
      ])
    ])
  ]
})
export class ProductsComponent implements OnInit {
  products: Product[] = [];
  pageNumber = 1;
  pageSize = 5;
  collectionSize: number;
  messageAr = '';
  messageEn = '';

  isCategoryProductExists = false;
  isProductExist = false;

  editingProductId: number | null = null;   // المنتج اللي في وضع التعديل
  editedProduct: Product = {} as Product;   // نسخة من المنتج اللي بيتعدل

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(( ) => this.handelAllAction(this.pageNumber));

  }
  constructor(private  productService: ProductService, private activatedRoute: ActivatedRoute,
              private cardService: CardService , private userService: UserService ) { }

  // tslint:disable-next-line:typedef
  handelAllAction(pageNumber) {
    const idCategoryExist = this.activatedRoute.snapshot.paramMap.has('id');
    const keyCategoryExist = this.activatedRoute.snapshot.paramMap.has('key');
    const key  = this.activatedRoute.snapshot.paramMap.get('key');
    if (idCategoryExist && keyCategoryExist && key && key !== '' ) {
      const idCategory  = this.activatedRoute.snapshot.paramMap.get('id');
      this.searchByCategoryIdAndKey(idCategory , key , pageNumber);
      return;
}

    if (idCategoryExist){
      const idCategory  = this.activatedRoute.snapshot.paramMap.get('id');
      this.getProductsByCategoryId(idCategory, pageNumber  );
    }else if (keyCategoryExist){
      // tslint:disable-next-line:no-shadowed-variable
      const key  = this.activatedRoute.snapshot.paramMap.get('key');
      if (key && key !== '' ) {
        this.getProductsByName(key, pageNumber);
      }
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
        if (this.products.length === 0) {
          this.isCategoryProductExists = true;
        }else {
          this.isCategoryProductExists = false;
        }
        this.isProductExist = false;
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
        if (this.products.length === 0) {
          this.isCategoryProductExists = true;
        }else {
          this.isCategoryProductExists = false;
        }
        this.isProductExist = false;
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
        this.isCategoryProductExists = false;
        this.isProductExist = false;
      },
      errors => {
        this.products = [];
        this.collectionSize = 0;
        // @ts-ignore
        this.messageAr =  errors.error.bundleMessage.message_ar;
        // @ts-ignore
        this.messageEn = errors.error.bundleMessage.message_en;

        this.isCategoryProductExists = false;
        this.isProductExist = true;
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
        this.isCategoryProductExists = false;
        this.isProductExist = false;
      },
      errors => {
        this.products = [];
        this.collectionSize = 0;
        // @ts-ignore
        this.messageAr = errors.error.bundleMessage.message_ar;
        // @ts-ignore
        this.messageEn = errors.error.bundleMessage.message_en;

        this.isCategoryProductExists = false;
        this.isProductExist = true;
      }
    );
  }
  // tslint:disable-next-line:typedef
  saveEdit() {
    this.productService.updateProduct(this.editedProduct).subscribe({
      next: (res) => {
        console.log('Updated:', res);
        const index = this.products.findIndex(p => p.id === this.editedProduct.id);
        if (index !== -1) {
          this.products[index] = { ...this.editedProduct };
        }

        this.editingProductId = null;
        this.isProductExist = false;
      },
      error: (err) => {
        console.error('Error updating:', err);

        if (err.error && err.error.errorMessages && err.error.errorMessages.length > 0) {
          this.messageAr = err.error.errorMessages[0].message_ar;
          this.messageEn = err.error.errorMessages[0].message_en;
        }

        this.isProductExist = true;
        setTimeout(() => {
          this.isProductExist = false;
        }, 3000);
      }
    });
  }



  // tslint:disable-next-line:typedef
  cancelEdit() {
    this.editingProductId = null;
  }
  // tslint:disable-next-line:typedef
  startEdit(product: Product) {
    this.editingProductId = product.id;
    this.editedProduct = { ...product };
  }
  // tslint:disable-next-line:typedef
  deleteProduct(id: number) {
    this.productService.deleteProduct(id).subscribe({
      next: (res) => {
        console.log('Deleted:', res);
        this.getAllProducts(this.pageNumber); // تحديث المنتجات
      },
      error: (err) => {
        console.error('Error deleting product:', err);
      }
    });
  }



  // tslint:disable-next-line:typedef
  addProductToCard(product: Product){
    const order = new CardOrder(product);
    this.cardService.addProductOrder(order);
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
  // tslint:disable-next-line:typedef
  isUserAdmin() {
    return this.userService.isUserAdmin();
  }

}
