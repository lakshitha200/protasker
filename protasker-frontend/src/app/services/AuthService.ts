// services/auth.service.ts
import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, map, Observable, of, tap } from 'rxjs';
import { SignUpRequest } from '../models/SignUpRequest';
import { environment } from '../environments/environment';
import { LoginRequest } from '../models/LoginRequest';
import { AuthResponse } from '../models/AuthResponse';
import { jwtDecode } from 'jwt-decode';
import { Router } from '@angular/router';
import { PasswordRestRequest } from '../models/PasswordResetRequest';

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

  private isAuthenticated = false;

  // // Token management
  // get accessToken(): string | null {
  //   return this.getCookie('access_token');
  // }

  // private getCookie(name: string): string | null {
  //   const match = document.cookie.match(new RegExp('(^| )' + name + '=([^;]+)'));
  //   return match ? match[2] : null;
  // }

  // // Method to check if the user is logged in (access token exists)
  // isAuthenticated(): boolean {
  //   return !!this.accessToken;
  // }

  // isTokenExpired(token: string): boolean {
  //   const decoded: any = jwtDecode(token);
  //   const currentTime = Math.floor(Date.now() / 1000);
  //   return decoded.exp < currentTime;
  // }

  // get refreshToken(): string | null {
  //   return localStorage.getItem(this.REFRESH_TOKEN_KEY);
  // }

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
  set setAccessToken(token: string){
    localStorage.setItem("JAT", token);
  }

  set setRefreshToken(token: string){
    localStorage.setItem("JRT", token);
  }

 

  // isLoggedIn(): boolean {
  //   const token = this.accessToken;
  //   return !!token;
  // }

  // logout(): void {
  //   localStorage.removeItem(this.ACCESS_TOKEN_KEY);
  //   localStorage.removeItem(this.REFRESH_TOKEN_KEY);
  //   this.router.navigateByUrl('/sign-in');
  // }

  signUp(userData: SignUpRequest): Observable<any> {
    console.log("works")
    return this.http.post<any>(this.registerUrl, userData);
  }

  // login(userData: LoginRequest): Observable<AuthResponse>{
  //   return this.http.post<AuthResponse>(this.loginUrl,userData);
  // }

  login(userData: LoginRequest): Observable<any>{
    return this.http.post<any>(this.loginUrl,userData,{ withCredentials: true });
  }

  
  
  getCurrentUser(): Observable<any> {
    return this.http.get<any>('http://localhost:8080/api/user/current-user',{ withCredentials: true });
  }
  
  // getRefreshToken(refreshToken: string): Observable<AuthResponse> {
  //   return this.http.post<AuthResponse>(`http://localhost:8080/api/auth/refresh?refreshToken=${refreshToken}`, {})
  // }

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