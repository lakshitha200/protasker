import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Notification } from '../models/Notification';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  private notifications$ = new BehaviorSubject<Notification[]>([]);
  private currentId = 0;

  get notifications(): Observable<Notification[]> {
    return this.notifications$.asObservable();
  }

  show(notification: Omit<Notification, 'id'>): number {
    const id = ++this.currentId;
    const notifications = [...this.notifications$.value, { ...notification, id }];
    this.notifications$.next(notifications);

    if (notification.duration && notification.duration > 0) {
      setTimeout(() => this.dismiss(id), notification.duration);
    }

    return id;
  }

  success(title: string, message: string, duration = 5000): number {
    return this.show({ title, message, type: 'success', duration });
  }

  error(title: string, message: string, duration = 8000): number {
    return this.show({ title, message, type: 'error', duration });
  }

  warning(title: string, message: string, duration = 6000): number {
    return this.show({ title, message, type: 'warning', duration });
  }

  info(title: string, message: string, duration = 4000): number {
    return this.show({ title, message, type: 'info', duration });
  }

  dismiss(id: number): void {
    const notifications = this.notifications$.value.filter(n => n.id !== id);
    this.notifications$.next(notifications);
  }

  clearAll(): void {
    this.notifications$.next([]);
  }
}