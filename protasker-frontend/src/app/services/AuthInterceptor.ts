// auth.interceptor.ts
import { HttpInterceptorFn } from '@angular/common/http';
import { AuthService } from './AuthService';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, finalize, reduce, switchMap, throwError } from 'rxjs';

let isVerificationInProgress = false;
export const authInterceptor: HttpInterceptorFn = (req, next) => {
  console.log("works authInterceptor")
  const authService = inject(AuthService);
  const router = inject(Router);

  console.log("Intercepting:", req.url);

  if (!req.url.includes('/api/')) {
    return next(req);
  }

  const jwtToken = authService.accessToken;
  const refreshToken = authService.refreshToken;
  console.log("refreshToken "+jwtToken);
  if (jwtToken && !authService.isTokenExpired(jwtToken)) {
    console.log("jwtToken");
        const clonedReq = req.clone({
        setHeaders: {
          Authorization: `Bearer ${jwtToken}`
        }
      });
      return next(clonedReq);
   
  }

  if (refreshToken && authService.isTokenExpired(jwtToken)) {
    return authService.getRefreshToken(refreshToken).pipe(
      switchMap(response => {
        localStorage.setItem('JAT', response.accessToken);
        const cloned = req.clone({
          setHeaders: { Authorization: `Bearer ${response.accessToken}` }
        });
        return next(cloned);
      }),
      catchError(err => {
        authService.logout();
        router.navigateByUrl('/sign-in');
        return throwError(() => err);
      })
    );
  }

  // // No token available at all
  return next(req);
};



