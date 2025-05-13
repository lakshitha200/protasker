import { Component, ViewChild } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/AuthService';
import { CommonModule } from '@angular/common';
import { validateActualData, validateEmptyFields, validateSyntax } from '../../util/validation';
import { getErrorMessage } from '../../util/error-messages';
import { LoaderService } from '../../services/Loader.service';
import { ModalService } from '../../services/Model.service';

@Component({
  selector: 'app-sign-in',
  imports: [RouterModule, FormsModule, CommonModule],
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.css'
})
export class SignInComponent {

  @ViewChild(NgForm) signInForm!: NgForm;
  
  submitted = false;
  usernameOrEmailError = "";
  passwordError = "";
  emailError = "";

  isOpen = false;

  constructor(private authService: AuthService, private router: Router,private loaderService: LoaderService, private modal: ModalService,) { }

  submitSignInForm() {
    this.submitted = true;
    let formValues = this.signInForm.value;
    console.log(formValues);

    // Reset errors
    this.usernameOrEmailError = this.passwordError = "";
    
    // Validate empty fields
    const emptyErrors = validateEmptyFields(formValues, "sign-in");
    Object.assign(this, emptyErrors);
    if (Object.keys(emptyErrors).length > 0) return;

    this.showLoader()
    this.authService.login(formValues).subscribe({
      next: response => {
        this.hideLoader();
        console.log('Signin successful!', response);
        this.router.navigateByUrl("/dashboard").then(() => {
          window.location.reload();
        });
        this.signInForm.reset();
      },
      error: err => {
        this.hideLoader();
        let error = JSON.stringify(err.error);
        let errorResponse = JSON.parse(error);
        const validationErrors = validateActualData(errorResponse, "sign-in");
        console.log(validationErrors);
        Object.assign(this, validationErrors);

        // this.usernameOrEmailError = true;
        // this.passwordError = true;
      }
    })
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

  //froget password
  openFrogetPasswordModel(){
    this.isOpen = true;
  }
  frogetPassword(emailInput:HTMLInputElement){
    // Reset errors
    this.emailError = "";
    
    // Validate empty fields
    const emptyErrors = validateEmptyFields(emailInput.value, "reset-password");
    Object.assign(this, emptyErrors);
    if (Object.keys(emptyErrors).length > 0) return;

    // Validate syntax error
    const syntaxErrors = validateSyntax(emailInput.value, "reset-password");
    Object.assign(this, syntaxErrors);
    if (Object.keys(syntaxErrors).length > 0) return;

    this.loaderService.show("fullscreen","spinner")
    const email:string = emailInput.value;
    console.log(email)
    this.authService.frogetPassword(email).subscribe({
      next: ()=>{
        this.loaderService.hide();
        this.close();
        alert("works");
      },
      error: ()=>{
        this.loaderService.hide();
        this.close()
        alert("fail");
      }
    })

  }
  close(){
    this.isOpen = false;
  }
  //-----------------------------------

}
