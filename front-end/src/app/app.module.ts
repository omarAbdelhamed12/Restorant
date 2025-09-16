import {RouterModule, Routes} from '@angular/router';
import {AppComponent} from './app.component';
import {NgModule} from '@angular/core';
import {ProductsComponent} from './componants/products/products.component';
import {HeaderComponent} from './componants/header/header.component';
import {CategoryComponent} from './componants/category/category.component';
import {CardDetailsComponent} from './componants/card-details/card-details.component';
import {CardComponent} from './componants/card/card.component';
import {BrowserModule} from '@angular/platform-browser';
import {FooterComponent} from './componants/footer/footer.component';
import { ChefsComponent } from './componants/chefs/chefs.component';
import { ContactInfoComponent } from './componants/contact-info/contact-info.component';
import {APP_BASE_HREF} from '@angular/common';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {NgbPaginationModule} from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from './componants/login/login.component';
import { SignupComponent } from './componants/signup/signup.component';
import {AuthInterceptor} from '../service/interceptor/auth.interceptor';
import {AuthGuard} from '../service/activetor/auth.guard';
import {LoginSignupGuard} from '../service/activetor/login-signup.guard';
import {FormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { ProductDetailsComponent } from './componants/product-details/product-details.component';
import { RequestLoginComponent } from './componants/request-login/request-login.component';
import { RequestAllOrderComponent } from './componants/request-all-order/request-all-order.component';


// http://localhost:4200/
export const routes: Routes = [

  // http://localhost:4200/active
  {path: 'products', component: ProductsComponent , canActivate: [AuthGuard] },
  {path: 'productDetails/:id', component: ProductDetailsComponent , canActivate: [AuthGuard]},
  {path: 'requestLogin', component: RequestLoginComponent , canActivate: [AuthGuard]},
  {path: 'requestAllOrder', component: RequestAllOrderComponent , canActivate: [AuthGuard]},
  {path: 'cardDetails', component: CardDetailsComponent , canActivate: [AuthGuard]},
  {path: 'contact-info', component: ContactInfoComponent , canActivate: [AuthGuard]},
  {path: 'chefs', component: ChefsComponent , canActivate: [AuthGuard]},
  {path: 'category/:id', component: ProductsComponent , canActivate: [AuthGuard]},
  {path: 'search/:key', component: ProductsComponent , canActivate: [AuthGuard]},
  {path: 'category/:id/search/:key', component: ProductsComponent , canActivate: [AuthGuard]},
  {path: 'login', component:  LoginComponent , canActivate: [LoginSignupGuard]},
  {path: 'sign-up', component: SignupComponent , canActivate: [LoginSignupGuard]},
  // http://localhost:4200/
  {path: '', redirectTo: '/products', pathMatch: 'full' },

  // if user enter thing without all routes
  {path: '**', redirectTo: '/products', pathMatch: 'full'  }

];

/*
*   // http://localhost:4200/
  {path: '', component:OrderItemsComponent}
* */
@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
    HeaderComponent,
    CategoryComponent,
    CardDetailsComponent,
    CardComponent,
    FooterComponent,
    ChefsComponent,
    ContactInfoComponent,
    LoginComponent,
    SignupComponent,
    ProductDetailsComponent,
    RequestLoginComponent,
    RequestAllOrderComponent

  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    HttpClientModule,
    NgbPaginationModule,
    FormsModule,
    BrowserAnimationsModule
  ],
  providers: [{ provide: APP_BASE_HREF, useValue: '/' },
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor , multi: true},
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
