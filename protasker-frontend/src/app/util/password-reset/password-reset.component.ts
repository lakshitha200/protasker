import { CommonModule } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { validateActualData, validateEmptyFields, validateSyntax } from '../validation';
import { ModalService } from '../../services/Model.service';
import { LoaderService } from '../../services/Loader.service';
import { AuthService } from '../../services/AuthService';
import { ActivatedRoute, Router } from '@angular/router';
import { PasswordRestRequest } from '../../models/PasswordResetRequest';

@Component({
  selector: 'app-password-reset',
  imports: [CommonModule, FormsModule],
  templateUrl: './password-reset.component.html',
  styleUrl: './password-reset.component.css'
})
export class PasswordResetComponent{

  @ViewChild('passwordResetForm') passwordResetForm: any;
  submitted = false;
  password = "";
  confirmPasswrod = "";
  passwordError = "";
  confirmPasswordError = "";

  constructor(private authService: AuthService,private modal: ModalService,private loaderService: LoaderService
    ,private activatedRoute:ActivatedRoute, private router:Router
  ) { }


  submitResetPasswordFrom(){
    const token = this.activatedRoute.snapshot.queryParamMap.get('token');
    if(token!=null){
      this.submitted = true;
      const formValues = this.passwordResetForm.value;
      this.password = formValues.password;
      this.confirmPasswrod = formValues.confirmPasswrod;

      // Reset errors
      this.passwordError = this.confirmPasswordError = "";

      // Validate empty fields
      const emptyErrors = validateEmptyFields(formValues, "new-password");
      Object.assign(this, emptyErrors);
      if (Object.keys(emptyErrors).length > 0) return;

      // Validate syntax
      const syntaxErrors = validateSyntax(formValues, "new-password");
      Object.assign(this, syntaxErrors);
      if (Object.keys(syntaxErrors).length > 0) return;

      this.showLoader();
      const data = new PasswordRestRequest(token,this.password);
      this.authService.resetPassword(data).subscribe({
        next:() => {
          this.hideLoader();
          this.passwordResetForm.reset();
          this.showModal("Password Rest Successfull",`Your password rest successfully`);
          this.router.navigateByUrl("/sign-in");
        },
        error: err => {
          this.hideLoader();
          let error = JSON.stringify(err.error);
          let errorResponse = JSON.parse(error);
          if("detail" in errorResponse){
            this.passwordResetForm.reset();
            this.showModal("Password Rest Fail!",errorResponse.detail);
          }else{
            const validationErrors = validateActualData(errorResponse, "new-password");
            Object.assign(this, validationErrors)
          }
        }
      });
    
    }
    
  }

   showModal(title:string,message:string) {
    this.modal.show(title, message);
  }

  // show and hide Sprinner (loader)
  showLoader() {
    this.loaderService.show('fullscreen', 'spinner');
  }

  hideLoader(){
    this.loaderService.hide(); 
  }
  //-----------------------------------

}
