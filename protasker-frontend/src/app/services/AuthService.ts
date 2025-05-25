// services/auth.service.ts
import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, catchError, map, Observable, of, tap } from 'rxjs';
import { SignUpRequest } from '../models/SignUpRequest';
import { environment } from '../environments/environment';
import { LoginRequest } from '../models/LoginRequest';
import { AuthResponse } from '../models/AuthResponse';
import { jwtDecode } from 'jwt-decode';
import { Router } from '@angular/router';
import { PasswordRestRequest } from '../models/PasswordResetRequest';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private http = inject(HttpClient);
  private router = inject(Router);
  private readonly ACCESS_TOKEN_KEY = environment.ACCESS_TOKEN_KEY;
  private readonly REFRESH_TOKEN_KEY = environment.REFRESH_TOKEN_KEY;
  private readonly registerUrl = environment.registerUrl;
  private readonly loginUrl = environment.loginUrl;
  private readonly verifyEmailUrl = environment.verifyEmailUrl;
  private readonly resendVerifyEmailUrl = environment.resendVerifyEmailUrl;
  private readonly forgotPasswordUrl = environment.forgotPasswordUrl;
  private readonly resetPasswordUrl = environment.resetPasswordUrl;

  private currentUserSubject = new BehaviorSubject<any>(null);
  public currentUser$ = this.currentUserSubject.asObservable();

  private isAuthenticated = false;

  constructor(){
    const storedUser = sessionStorage.getItem('currentUser');
    if (storedUser) {
      this.currentUserSubject.next(JSON.parse(storedUser));
    }
  }
 
  checkAuthStatus(): Observable<boolean> {
    console.log("Works checkAuthStatus");
    return this.http.get<boolean>("http://localhost:8080/api/auth/check", { withCredentials: true });
  }

   // Method to logout the user (clear cookies)
  logout() {
    document.cookie = 'access_token=; Max-Age=-1; path=/';  // Remove access token
    document.cookie = 'refresh_token=; Max-Age=-1; path=/';  // Remove refresh token
    this.router.navigate(['/login']);
  }
  
 
  signUp(userData: SignUpRequest): Observable<any> {
    console.log("works")
    return this.http.post<any>(this.registerUrl, userData);
  }

  login(userData: LoginRequest): Observable<any>{
    return this.http.post<any>(this.loginUrl,userData,{ withCredentials: true });
  }

  
  
  fetchCurrentUser(): Observable<User> {
    return this.http.get<any>('http://localhost:8080/api/user/current-user',{ withCredentials: true }).pipe(
      tap((user) => {
        this.setCurrentUser(user); // Cache the user
      })
    );;
  }

  // Set user in memory & sessionStorage
  setCurrentUser(user: any) {
    this.currentUserSubject.next(user);
    sessionStorage.setItem('currentUser', JSON.stringify(user)); // Persist in session
  }

  // Get current user synchronously (if needed)
  getCurrentUser() {
    return this.currentUserSubject.value;
  }
  
  getRefreshToken(): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`http://localhost:8080/api/auth/refresh`, {},{ withCredentials: true })
  }

  verifyEmail(token: string | null): Observable<any>{
    return this.http.post<any>(this.verifyEmailUrl,{token});
  }

  resendVerifyEmail(email: string): Observable<any>{
    return this.http.post<any>(this.resendVerifyEmailUrl,{email});
  }

  frogetPassword(email: string): Observable<any>{
    return this.http.post<any>(this.forgotPasswordUrl,{email});
  }

  resetPassword(data: PasswordRestRequest): Observable<any>{
    return this.http.post<any>(this.resetPasswordUrl,data);
  }

  getTest():Observable<any>{
    console.log('getTest() called');
    return this.http.get<any>("http://localhost:8080/api/auth/test");
  }
  

}