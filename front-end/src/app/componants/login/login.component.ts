import { Component, OnInit } from '@angular/core';
import {UserService} from '../../../service/security/user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  messageAr = '';
  messageEn = '';
  isUserNameValid = false;
  isPasswordValid = false;
  constructor(private userService: UserService, private router: Router) {
  }

  ngOnInit(): void {
  }

// tslint:disable-next-line:typedef
  login(userName, password ) {
    if (!this.validation(userName, password)) {
      return;
    }
    this.userService.login(userName, password ).subscribe(
      response => {
        sessionStorage.setItem('token', response.token);
        sessionStorage.setItem('userName', response.userName);
        this.router.navigateByUrl('products');
      }, errors => {
        this.messageAr = errors.error.bundleMessage.message_ar;
        this.messageEn = errors.error.bundleMessage.message_en;
        // setTimeout(() => {
        //     this.messageAr = '';
        //     this.messageEn = '';
        //   }, 5000);
      }
    );
  }

  validation(userName, password ): boolean {
    if (!userName) {
      // this.messageAr = 'اسم المستخدم اجبارى';
      // this.messageEn = ' userName is required';
      this.isUserNameValid = true;
      setTimeout(() => {
          // this.messageAr = '';
          // this.messageEn = '';
          this.isUserNameValid = false;
        }, 3000
      );
      return false;
    }
    if (!password) {
      this.isPasswordValid = true;
      setTimeout(() => {
          this.isPasswordValid = false;
        }, 3000
      );
      return false;
    }
    return true;
  }
  // tslint:disable-next-line:typedef
  clear(){
    this.messageAr = '';
    this.messageEn = '';
  }


}
