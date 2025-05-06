import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotificationComponent } from '../notification/notification.component';
import { NotificationService } from '../../services/Notification.service';


@Component({
  selector: 'app-notification-container',
  imports: [NotificationComponent,CommonModule],
  templateUrl: './notification-container.component.html',
  styleUrl: './notification-container.component.css'
})
export class NotificationContainerComponent {
  notifications$: any;
  constructor(private notificationService: NotificationService) {
    this.notifications$ = this.notificationService.notifications;
  }
}
