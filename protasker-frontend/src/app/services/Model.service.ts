import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ModalService {
  isOpen = false;
  title = '';
  message = '';
  typeConfirm = false;
  private confirmAction = new Subject<boolean>(); // Properly initialized

  show(title: string, message: string, typeConfirm = false) {
    this.isOpen = true;
    this.title = title;
    this.message = message;
    this.typeConfirm = typeConfirm;
    return this.confirmAction.asObservable(); // Return as observable
  }

  confirm(title: string, message: string): Subject<boolean> {
    this.show(title, message, true);
    return this.confirmAction;
  }

  respond(response: boolean) {
    this.confirmAction.next(response);
    this.hide();
  }

  hide() {
    this.isOpen = false;
    this.confirmAction.complete(); // Prevent memory leaks
    this.confirmAction = new Subject<boolean>(); // Reinitialize for next use
  }
}