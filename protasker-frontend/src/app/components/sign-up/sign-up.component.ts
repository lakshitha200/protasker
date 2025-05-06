import { Component, OnInit, ViewChild } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../services/AuthService';
import { SignUpRequest } from '../../models/SignUpRequest';
import { CommonModule } from '@angular/common';
import { validateActualData, validateEmptyFields, validateSyntax } from '../../util/validation';
import { ModalService } from '../../services/Model.service';
import { LoaderService } from '../../services/Loader.service';
import { LoaderSprinnerComponent } from '../../util/loader-sprinner/loader-sprinner.component';

@Component({
  selector: 'app-sign-up',
  imports: [RouterModule, CommonModule, FormsModule,LoaderSprinnerComponent],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css'
})
export class SignUpComponent{

  @ViewChild(NgForm) signupForm!: NgForm;

  submitted = false;
  username = "";
  email = "";
  password = "";
  confirmPasswrod = "";

  usernameError = "";
  emailError = "";
  passwordError = "";
  confirmPasswordError = "";

  constructor(private authService: AuthService,private modal: ModalService,private loaderService: LoaderService) { }

  submitSignUpFrom() {
    this.submitted = true;

    const formValues = this.signupForm.value;
    this.username = formValues.username;
    this.email = formValues.email;
    this.password = formValues.password;
    this.confirmPasswrod = formValues.confirmPasswrod;

    // Reset errors
    this.usernameError = this.emailError = this.passwordError = this.confirmPasswordError = "";

    // Validate empty fields
    const emptyErrors = validateEmptyFields(formValues, "sign-up");
    Object.assign(this, emptyErrors);
    if (Object.keys(emptyErrors).length > 0) return;

    // Validate syntax
    const syntaxErrors = validateSyntax(formValues, "sign-up");
    Object.assign(this, syntaxErrors);
    if (Object.keys(syntaxErrors).length > 0) return;

    const formData = new SignUpRequest(this.username, this.email, this.password);
    this.showLoader();
    this.authService.signUp(formData).subscribe({
      next:() => {
        this.hideLoader();
        this.signupForm.reset();
        this.showModal("Verify Your Email",`We sent a verification link to ${this.email}. Check your inbox!`);
      },
      error: err => {
        this.hideLoader();
        console.log(err);
        let error = JSON.stringify(err.error);
        let errorResponse = JSON.parse(error);
        const validationErrors = validateActualData(errorResponse, "sign-up");
        Object.assign(this, validationErrors)
      }
    });

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


  // loadInlineData() {
  //   // Show an inline loader with a bar
  //   this.loaderService.showLoader('inline', 'bar');

  //   // Simulate an API call
  //   setTimeout(() => {
  //     this.loaderService.hideLoader();  // Hide after 3 seconds
  //   }, 3000);
  // }
  

}
