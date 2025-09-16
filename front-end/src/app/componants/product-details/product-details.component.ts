import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ProductDetailsService } from '../../../service/product-details.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {
  messageAr = '';
  messageEn = '';

  // flags for validation
  isIngredientsValid = false;
  isCaloriesValid = false;
  isPreparationTimeValid = false;

  productDetails = {
    ingredients: '',
    calories: null,
    preparationTime: null,
    productId: null
  };

  constructor(
    private productDetailsService: ProductDetailsService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // استقبل id من ال route
    const productId = this.route.snapshot.paramMap.get('id');
    if (productId) {
      this.productDetails.productId = +productId;

      // نجيب تفاصيل المنتج لو موجودة
      this.productDetailsService.getProductDetailsById(+productId).subscribe({
        next: (res) => {
          if (res) {
            this.productDetails = {
              ingredients: res.ingredients,
              calories: res.calories,
              preparationTime: res.preparationTime,
              productId: res.productId
            };
          }
        },
        error: (err) => {
          console.error('No details found or error fetching', err);
        }
      });
    }
  }

  // tslint:disable-next-line:typedef
  saveDetails() {
    if (!this.validation()) {
      return;
    }

    this.productDetailsService.addProductDetails(this.productDetails).subscribe({
      next: (res) => {
        console.log('Saved successfully', res);
        this.router.navigate(['/products']);
      },
      error: (err) => {
        this.messageAr = err.error.errorMessages[0].message_ar;
        this.messageEn = err.error.errorMessages[0].message_en;
      }
    });
  }

  // validation function
  validation(): boolean {
    if (!this.productDetails.ingredients || this.productDetails.ingredients.trim() === '') {
      this.isIngredientsValid = true;
      setTimeout(() => (this.isIngredientsValid = false), 3000);
      return false;
    }

    // هنا مش هنقيّد الأرقام بأي شرط
    if (this.productDetails.calories === null || this.productDetails.calories === undefined) {
      this.isCaloriesValid = true;
      setTimeout(() => (this.isCaloriesValid = false), 3000);
      return false;
    }

    if (this.productDetails.preparationTime === null || this.productDetails.preparationTime === undefined) {
      this.isPreparationTimeValid = true;
      setTimeout(() => (this.isPreparationTimeValid = false), 3000);
      return false;
    }

    return true;
  }


  // tslint:disable-next-line:typedef
  clear() {
    this.messageAr = '';
    this.messageEn = '';
  }
}
