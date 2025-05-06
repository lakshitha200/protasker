import { Component, Input } from '@angular/core';
import { NotificationType } from '../../models/Notification';


@Component({
  selector: 'app-notification',
  imports: [],
  templateUrl: './notification.component.html',
  styleUrl: './notification.component.css'
})
export class NotificationComponent {
  @Input() notification: any;
  isClosing = false;
  notificationService: any;

  getIconPath(): string {
    const icons = {
      success: 'success-filled.png',
      error: 'assets/icons/error.png',
      warning: 'assets/icons/warning.png',
      info: 'assets/icons/notification-blue.png'
    };
        return icons[this.notification.type as NotificationType] || icons.info;
  }

  dismiss(): void {
    this.isClosing = true;
    setTimeout(() => {
      this.notificationService.dismiss(this.notification.id);
    }, 200);
  }
}
