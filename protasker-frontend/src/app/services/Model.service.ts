import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class ModalService {
  isOpen = false;
  title = '';
  message = '';

  show(title: string, message: string) {
    this.isOpen = true;
    this.title = title;
    this.message = message;
  }

  hide() {
    this.isOpen = false;
  }
}