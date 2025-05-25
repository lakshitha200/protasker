
import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from './AuthService';
import { catchError, map, Observable, of } from 'rxjs';


export const authGuard: CanActivateFn = (): Observable<boolean> => {
  const authService = inject(AuthService);
  const router = inject(Router);

  return authService.checkAuthStatus().pipe(
    map(res => {
      console.log(res);
      return res;
    }),
    catchError(err => {
          console.log(err);
      router.navigate(['/sign-in']);
      return of(false); // Block access
    })
  );
};

 
  
