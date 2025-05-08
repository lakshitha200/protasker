import { Component, ViewChild } from '@angular/core';
import { Router, RouterLink, RouterModule, RouterOutlet } from '@angular/router';
import { HeaderComponent } from './components/header/header.component';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { ModelComponent } from './util/model/model.component';
import { LoaderSprinnerComponent } from './util/loader-sprinner/loader-sprinner.component';
import { NotificationContainerComponent } from './util/notification-container/notification-container.component';
import { NotificationService } from './services/Notification.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet,CommonModule, HeaderComponent, SidebarComponent, ModelComponent, LoaderSprinnerComponent, NotificationContainerComponent],
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
      // hide header
      if(this.router.url === '/dashboard' || this.router.url === '/project' || this.router.url === '/notification'
        || this.router.url === '/user-profile' 
      ){
        this.showHeader = false;
        this.showSideBar = true;
      }else if(this.router.url.includes("verify-email")){
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