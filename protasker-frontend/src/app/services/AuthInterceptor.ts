// auth.interceptor.ts
import { HttpInterceptorFn } from '@angular/common/http';
import { AuthService } from './AuthService';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, finalize, reduce, switchMap, throwError } from 'rxjs';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);  // Inject AuthService
  const router = inject(Router);  // Inject Router

  // Skip token refresh for auth endpoints to avoid loops
   if (req.url.includes('/auth/refresh') || req.url.includes('/auth/login')) {
    return next(req);
  }

  return next(req).pipe(
    catchError((err) => {
      console.log("Works Inspector");
      // if (err.status === 401) {
        
      //   console.log("yes")
      //   // Attempt token refresh
      //   return authService.getRefreshToken().pipe(
      //     switchMap(() => next(req)), // Retry original request (cookies auto-send)
      //     catchError((refreshErr) => {
      //       // Redirect to login if refresh fails
      //       authService.logout();
      //       router.navigate(['/sign-in']);
      //       return throwError(() => refreshErr);
      //     })
      //   );
      // }
      return throwError(() => err);
    })
  );
};



