import { Component, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/AuthService';
import { jwtDecode } from 'jwt-decode';
import { ModalService } from '../../services/Model.service';
import { LoaderService } from '../../services/Loader.service';

@Component({
  selector: 'app-verification',
  imports: [CommonModule,RouterModule],
  templateUrl: './verification.component.html',
  styleUrls: ['./verification.component.css']
})
export class VerificationComponent implements OnInit {
  state: 'success' | 'error' | undefined;
  title = '';
  message = '';
  email: string = '';

  constructor(private authService:AuthService ,private activatedRoute: ActivatedRoute, 
    private modal: ModalService, private loaderService: LoaderService, private router:Router) {}

  ngOnInit(): void {

    try {
    const token = this.activatedRoute.snapshot.queryParamMap.get('token');
    if (token) {
      const decodedToken = jwtDecode(token) as { type: string, sub: string };
      if (decodedToken.type === "email_verification") {
        this.email = decodedToken.sub;
        this.authService.verifyEmail(token).subscribe({
          next: (res) => {
            this.state = "success";
            this.title = "Email Verified!";
            this.message = "Your account is now active. You’ll be redirected shortly.";
            setTimeout(()=>{
              this.router.navigateByUrl("/dashboard");
            },3000)
          },
          error: (err) => {
            alert("Verification Failed! Invalid or corrupted verification link.")
            this.state = "error";
            this.title = "Verification Failed!";
            this.message = "This link is invalid or expired.";
          }
        });
      }}
  } catch (e) {
    console.error("Invalid token", e);
    alert("Verification Failed! Invalid or corrupted verification link.");
    this.state = "error";
    this.title = "Verification Failed!";
    this.message = "Invalid or corrupted verification link.";
  }

  }


  resendVerificationEmail(){
      this.showLoader();
      this.authService.resendVerifyEmail(this.email).subscribe({
        next: (res)=>{
          this.hideLoader();
          this.showModal("Verify Your Email",`We sent a verification link to ${this.email}. Check your inbox!`);
        },
        error: (err)=>{
          this.hideLoader();
          this.showModal("Verification Link Send Fail!",`Verification link send fail. Try again.`);
        }
      })
  }

  showModal(title:string,message:string) {
    this.modal.show(title, message,false);
  }
  
  showLoader() {
    this.loaderService.show('fullscreen', 'spinner');
  }
  
  hideLoader(){
    this.loaderService.hide(); 
  }

}
