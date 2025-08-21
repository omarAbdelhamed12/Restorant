import { Component, OnInit } from '@angular/core';
import {UserService} from '../../../service/security/user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  messageAr = '';
  messageEn = '';
  isUserNameValid = false;
  isPasswordValid = false;
  isConfirmPasswordValid = false;
  isMatchValid = false;
  isAddressValid = false;
  isAgeValid = false;
  isPhoneNumberValid = false;

  constructor(private userService: UserService, private router: Router) {
  }

  ngOnInit(): void {
  }

// tslint:disable-next-line:typedef
  creatUser(userName, password, confirmPassowrd, address, age, phoneNumber) {
    if (!this.validation(userName, password, confirmPassowrd, address, age, phoneNumber)) {
      return;
    }
    this.userService.creatUser(userName, password, address, age, phoneNumber).subscribe(
      response => {
         sessionStorage.setItem('token', response.token);
         sessionStorage.setItem('userName', response.userName);
         this.router.navigateByUrl('products');
      }, errors => {
        this.messageAr = errors.error.errorMessages[0].message_ar;
        this.messageEn = errors.error.errorMessages[0].message_en;
        // setTimeout(() => {
        //     this.messageAr = '';
        //     this.messageEn = '';
        //   }, 5000);
      }
    );
  }

  validation(userName, password, confirmPassowrd, address, age, phoneNumber): boolean {
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
    if (!confirmPassowrd) {
      this.isConfirmPasswordValid = true;
      setTimeout(() => {
          this.isConfirmPasswordValid = false;
        }, 3000
      );
      return false;
    }
    if (password !== confirmPassowrd) {
      this.isMatchValid = true;
      setTimeout(() => {
          this.isMatchValid = false;
        }, 3000
      );
      return false;
    }
    if (!address) {
      this.isAddressValid = true;
      setTimeout(() => {
          this.isAddressValid = false;
        }, 3000
      );
      return false;
    }
    if (!age) {
      this.isAgeValid = true;
      setTimeout(() => {
          this.isAgeValid = false;
        }, 3000
      );
      return false;
    }
    if (!phoneNumber) {
      this.isPhoneNumberValid = true;
      setTimeout(() => {
          this.isPhoneNumberValid = false;
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
