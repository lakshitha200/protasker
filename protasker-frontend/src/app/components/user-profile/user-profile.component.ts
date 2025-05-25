import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { AuthService } from '../../services/AuthService';
import { User } from '../../models/User';
import { CommonModule } from '@angular/common';
import { ModalService } from '../../services/Model.service';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/User.service';
import { LoaderService } from '../../services/Loader.service';
import { concatMap, of } from 'rxjs';

@Component({
  selector: 'app-user-profile',
  imports: [CommonModule, FormsModule],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css'
})
export class UserProfileComponent implements OnInit{

  @ViewChild("userUpdateForm") userUpdateForm!: HTMLFormElement;
  @ViewChild("imageUploader") imageUploader!: ElementRef<HTMLInputElement>;
  

  activeUser!: User;
  cacheUser!:User;
  joinDate = "";
  lastLogin = "";
  isOpen:boolean = false;
  selectedFile!: File | null;
  imagePreview!: string | ArrayBuffer | null;
  newSkill: string = '';
  showSkills: string[] = [];
  profilePicture!: string | null;
  defaultProfilePicture: string = '../uploads/profile-pictures/user.png';
  updateDP!: string | null;

  constructor(private authService:AuthService, private modal: ModalService,
              private userService: UserService, private loaderService: LoaderService){}

  ngOnInit(): void {
    this.authService.fetchCurrentUser().subscribe();
    this.takeCurrentUser();
  }

  takeCurrentUser() {
    this.authService.currentUser$.subscribe({
      next: (res)=>{
        this.activeUser = res;
        this.cacheUser = JSON.parse(JSON.stringify(res));;
        const createdAt = new Date(res.createdAt);
        const lastLogin = new Date(res.lastLogin);
        const now = new Date();
        const diffInHours = Math.abs(now.getTime() - lastLogin.getTime()) / (1000 * 60 * 60);

        this.joinDate = createdAt.toLocaleDateString('en-US', {
          month: 'long',   // Full month name (e.g., "May")
          day: 'numeric',  // Day as number (e.g., "19")
          year: 'numeric'  // Full year (e.g., "2025")
        });

        const timeStr = lastLogin.toLocaleTimeString('en-US', {
          hour: 'numeric',
          minute: '2-digit',
          hour12: true
        });

        // Check if it's today
        if (lastLogin.toDateString() === now.toDateString()) {
          this.lastLogin= `Today, ${timeStr}`;
        } 
        // Check if it was yesterday
        else if (diffInHours < 24 && lastLogin.getDate() !== now.getDate()) {
          this.lastLogin=  `Yesterday, ${timeStr}`;
        }
        // Otherwise show full date
        else {
          this.lastLogin=  lastLogin.toLocaleDateString('en-US', {
            month: 'short',
            day: 'numeric',
            year: 'numeric'
          }) + `, ${timeStr}`;
        }
        console.log(this.activeUser);
        this.showSkills = this.activeUser.skills;
        this.profilePicture = ".."+this.activeUser.profilePicture;
        this.updateDP = ".."+this.activeUser.profilePicture;
      },
      error: (err)=>{
        
      }
    })
  }

  updateFormOpen(){
    this.isOpen = true;
  }

  close(){
      this.isOpen = false;
  }

  uploadProfileImage(event: Event){
    event.preventDefault();
    console.log(this.imageUploader)
    this.imageUploader.nativeElement.click();
  }

  handleFileInput(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files[0]) {
      const file = input.files[0];

      const reader = new FileReader();
    
      reader.onload = () => {
        this.imagePreview = reader.result as string;
      };
    
      reader.readAsDataURL(file);
      
      // Validate file
      if (file.size > 5 * 1024 * 1024) {
        this.showModal("Maximum File Size Exceeded","File is too large! Max 5MB allowed.",false)
        return;
      }
      
      if (!file.type.match('image/jpeg') && !file.type.match('image/png')) {
        this.showModal("Invalid File Type","Only JPG or PNG files are allowed!",false)
        return;
      }
      this.selectedFile = file;
    }
  }

  removeImage() {
    const confirmation$ = this.modal.confirm(
      "Delete Profile Picture", 
      "Are you sure you want to remove your profile picture?",
    );
    confirmation$.subscribe(confirm=>{
      if(confirm){
        this.updateDP = null;
        this.imagePreview = null;
        this.selectedFile = null;
        this.imageUploader.nativeElement.value = '';
      }
    })

  }

  addSkill(event: Event): void {
     event.preventDefault();
    const skill = this.newSkill.trim();
    if (skill && !this.showSkills.includes(skill)) {
      this.showSkills.push(skill);
    }
    this.newSkill = '';
  }

  removeSkill(index: number): void {
    this.showSkills.splice(index, 1);
  }
  submitUserUpdateFrom(){
    console.log(this.userUpdateForm);

    const formData = new FormData();
    if (this.selectedFile) {
      formData.append('file', this.selectedFile);
    }
    // delete otherFormData.profilePicture;
    const otherFormData = {...this.userUpdateForm['value'], skills: this.showSkills};
    delete otherFormData.profilePicture;
    
    console.log('Form Data:', otherFormData);
    this.showLoader();
    let update$ = of(null); // start with an empty observable
    if(this.selectedFile==null &&
      this.cacheUser.firstName==otherFormData.firstName && 
      this.cacheUser.lastName==otherFormData.lastName && 
      this.cacheUser.department==otherFormData.department && 
      this.cacheUser.position==otherFormData.position && 
      this.cacheUser.phoneNumber==otherFormData.phoneNumber && 
      JSON.stringify(this.cacheUser.skills) == JSON.stringify(otherFormData.skills)
    ){
      this.hideLoader();
      this.showModal("No Changes","You haven't made any changes to update.",false);
      return;
    }

    this.hideLoader();
    const confirmation$ = this.modal.confirm(
      "Update User", 
      "Are you sure you want to update these details?",
    );

    confirmation$.subscribe((confirmed) => {
      if (confirmed) {
        this.showLoader();
        if (formData.has('file')) {
        	update$ = this.userService.updateUserProfilePicture(this.activeUser.userId, formData);
        }
        update$
            .pipe(
              concatMap(() => {
                if (Object.keys(otherFormData).length > 0) {
                  return this.userService.updateUser(this.activeUser.userId, otherFormData);
                }else{
                  window.location.reload();
                  return of(null);
                }
                
              })
            )
            .subscribe({
              next: () => {
                window.location.reload();
              },
              error: (err) => {
                this.hideLoader();
                this.showModal("Failed","Failed to update profile. Please try again later.",false)
                console.error(err);
              }
            });
      } 
    });
  }
  

  showModal(title:string,message:string,typeConfirm:boolean) {
    this.modal.show(title, message,typeConfirm);
  }

  // show and hide Sprinner (loader)
  showLoader() {
    this.loaderService.show('fullscreen', 'spinner');
  }

  hideLoader(){
    this.loaderService.hide(); 
  }
  
}
