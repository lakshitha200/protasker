import { Injectable } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from './AuthService';


export const authGuard: CanActivateFn = (): boolean => {
    const token = localStorage.getItem('JAT');
    const auth = inject(AuthService);
    const router = inject(Router);
  
    if (!auth.isLoggedIn()) {
      router.navigateByUrl('/sign-in');
      return false;
    }
    return true;
  };

 
  
