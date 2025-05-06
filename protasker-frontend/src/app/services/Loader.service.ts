import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

export type LoaderType = 'spinner' | 'dots' | 'bar';
export type LoaderMode = 'fullscreen' | 'inline';

export interface LoaderState {
  loading: boolean;
  mode: LoaderMode;
  type: LoaderType;
}

@Injectable({ providedIn: 'root' })
export class LoaderService {
  private loaderSubject = new BehaviorSubject<LoaderState>({ 
    loading: false,
    mode: 'fullscreen',
    type: 'spinner'
  });
  
  loaderState$ = this.loaderSubject.asObservable();

  show(mode: LoaderMode = 'fullscreen', type: LoaderType = 'spinner') {
    this.loaderSubject.next({ loading: true, mode, type });
  }

  hide() {
    this.loaderSubject.next({ loading: false, mode: 'fullscreen', type: 'spinner' });
  }
}