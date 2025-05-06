import {  ChangeDetectorRef, Component, Inject, inject, NgZone, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../services/AuthService';
import { CommonModule } from '@angular/common';
import { Subject, switchMap, takeUntil, timer } from 'rxjs';
import { jwtDecode } from 'jwt-decode';
import { ModalService } from '../../services/Model.service';
import { LoaderService } from '../../services/Loader.service';
import { FormsModule, NgForm } from '@angular/forms';
import { validateActualData, validateEmptyFields, validateSyntax } from '../validation';


@Component({
  selector: 'app-verification',
  imports: [CommonModule,FormsModule],
  templateUrl: './verification.component.html',
  styleUrls: ['./verification.component.css']
})
export class VerificationComponent implements OnInit,OnDestroy  {

  @ViewChild(NgForm) signupForm!: NgForm;

  private destroy$ = new Subject<void>();
  constructor( private cdRef: ChangeDetectorRef, private ngZone: NgZone,private route: ActivatedRoute, private authService: AuthService, private router: Router
    ,private modalService :ModalService, private loaderService :LoaderService
  ) {}

  isComplete: boolean = false;
  isError: boolean = false;
  email: string ="";
  submitted:boolean = false;
  passwordError: string = "";
  confirmPasswordError: string = "";
  password:string ="";
  confirmPasswrod:string = "";

  ngOnInit(): void {

    
    this.isComplete= false;
    this.isError = false;
    console.log(this.route.snapshot)
    // const token = this.route.snapshot.queryParamMap.get('token');

    // if (token) {
    //   const decoded: any = jwtDecode(token);
    //   console.log(decoded)
    //   if(decoded.type != null && decoded.type != "" ){
    //     if(decoded.type == "email_verification"){
    //       this.email = decoded.sub;
    //     }
    //   }
    //    // or decoded.email if you used a custom claim
      
    //   this.authService.verifyEmail(token)
    //   .pipe(takeUntil(this.destroy$))
    //   .subscribe({
    //     next: () => {
    //       this.isComplete = true;

    //       // Let Angular update the UI before navigating
    //       timer(3000).subscribe(() => {
    //         this.ngZone.run(() => {
    //           this.router.navigate(['/dashboard']);
    //         });
    //       });
    //     },
    //     error: () => {
    //       this.isError = true;
    //     }
    //   });
    // }
  }

  resendVerificationEmail(){
    console.log("works");
    this.loaderService.show("fullscreen","spinner");
    this.authService.resendVerifyEmail(this.email).subscribe({
      next: () => {
        this.loaderService.hide();
        this.modalService.show("Verification Email Sent","A new verification email has been sent. Please check your inbox.");
      },
      error: () =>{
        this.loaderService.hide();
        this.modalService.show("Failed to Resend Email","We couldn't resend the verification email. Please try again later.");
      }
    })
  }

  submitResetPasswordFrom(){
    this.submitted = true;
    const formValues = this.signupForm.value;
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

    this.loaderService.show("fullscreen","spinner")
    const token = this.route.snapshot.queryParamMap.get('token');
    if(token!=null && token != ""){
      this.authService.resetPassword(token,this.password).subscribe({
        next: (res) =>{
          this.loaderService.hide();
          alert("nice")
        },
        error: err => {
          this.loaderService.hide();
          let error = JSON.stringify(err.error);
          let errorResponse = JSON.parse(error);
          console.log(errorResponse);
          const validationErrors = validateActualData(errorResponse, "new-password");
          Object.assign(this, validationErrors)
        },
        complete: () => { console.log('Done'); }
      })
    }else{
      this.loaderService.hide();
      alert("token error");
    }

   

  

    
  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }

}

