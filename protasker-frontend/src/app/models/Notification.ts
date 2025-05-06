export type NotificationType = 'success' | 'error' | 'warning' | 'info';

export interface Notification {
  id?: number;
  title: string;
  message: string;
  type: NotificationType;
  duration?: number; // in ms (0 = persistent)
  icon?: string;
}