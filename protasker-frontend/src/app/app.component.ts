import { Component, ViewChild } from '@angular/core';
import { Router, RouterLink, RouterModule, RouterOutlet } from '@angular/router';
import { HeaderComponent } from './components/header/header.component';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { ModelComponent } from './util/model/model.component';
import { LoaderSprinnerComponent } from './util/loader-sprinner/loader-sprinner.component';
import { NotificationService } from './services/Notification.service';
import { FooterComponent } from './components/footer/footer.component';


@Component({
  selector: 'app-root',
  imports: [RouterOutlet,CommonModule, HeaderComponent, FooterComponent, SidebarComponent, ModelComponent, LoaderSprinnerComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'protasker-frontend';

  @ViewChild("sideViewComponents") sideViewComponents:any;

  showHeader = true;
  showSideBar = true;
  showModelWindow = true;
  isLoading = false;
  isSidebarCollapsed = false;

  ngOnInit() {
      // Simple success notification
this.notification.success('Welcome!', 'You have successfully logged in');

// Custom notification
this.notification.show({
  title: 'Profile Updated',
  message: 'Your changes have been saved',
  type: 'success',
  duration: 3000
});

// Error notification
this.notification.error('Error', 'Failed to save changes');
  }
  constructor(private router: Router,private notification: NotificationService) {
    this.router.events.subscribe(() => {
      console.log(this.router.url)
      // hide header
      if(this.router.url === '/dashboard' ||this.router.url === '/project' || this.router.url === '/project/project-create' || this.router.url === '/notification' || this.router.url.includes('/test2')
        || this.router.url === '/user-profile'   || this.router.url.includes('/agile' )  || this.router.url.includes('/workspace') || this.router.url.includes('/test')  || this.router.url.includes('/test') || this.router.url.includes('/test3')  
      ){
        this.showHeader = false;
        this.showSideBar = true;
      }else if(this.router.url.includes("verify-email") || this.router.url.includes("reset-password") ){
        this.showHeader = false;
        this.showSideBar = false;
      }else{
        this.showHeader = true;
        this.showSideBar = false;
      }
    });

  }

  handlesideBarEvent() {
    this.isSidebarCollapsed = !this.isSidebarCollapsed;
  }
}