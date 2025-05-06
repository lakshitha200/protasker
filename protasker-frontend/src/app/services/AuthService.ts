// services/auth.service.ts
import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SignUpRequest } from '../models/SignUpRequest';
import { environment } from '../environments/environment';
import { LoginRequest } from '../models/LoginRequest';
import { AuthResponse } from '../models/AuthResponse';
import { jwtDecode } from 'jwt-decode';
import { Router } from '@angular/router';

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

  // Token management
  get accessToken(): string | null {
    return localStorage.getItem(this.ACCESS_TOKEN_KEY);
  }
  get refreshToken(): string | null {
    return localStorage.getItem(this.REFRESH_TOKEN_KEY);
  }
  set setAccessToken(token: string){
    localStorage.setItem("JAT", token);
  }

  set setRefreshToken(token: string){
    localStorage.setItem("JRT", token);
  }

 

  isLoggedIn(): boolean {
    const token = this.accessToken;
    return !!token;
  }

  logout(): void {
    localStorage.removeItem(this.ACCESS_TOKEN_KEY);
    localStorage.removeItem(this.REFRESH_TOKEN_KEY);
    this.router.navigateByUrl('/sign-in');
  }

  signUp(userData: SignUpRequest): Observable<any> {
    console.log("works")
    return this.http.post<any>(this.registerUrl, userData);
  }

  login(userData: LoginRequest): Observable<AuthResponse>{
    return this.http.post<AuthResponse>(this.loginUrl,userData);
  }

  isTokenExpired(token: any): boolean {
    const decoded: any = jwtDecode(token);
    const currentTime = Math.floor(Date.now() / 1000);
    return decoded.exp < currentTime;
  }
  
  getCurrentUser(): Observable<any> {
    return this.http.get<any>('http://localhost:8080/api/user/current-user');
  }
  
  getRefreshToken(refreshToken: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`http://localhost:8080/api/auth/refresh?refreshToken=${refreshToken}`, {})
  }

  verifyEmail(token: string | null): Observable<any>{
    return this.http.get<any>(this.verifyEmailUrl+"?token="+token);
  }

  resendVerifyEmail(email: string): Observable<any>{
    return this.http.post<any>(this.resendVerifyEmailUrl,{email});
  }

  frogetPassword(email: string): Observable<any>{
    return this.http.post<any>(this.forgotPasswordUrl,{email});
  }

  resetPassword(token: string, newPassword:string): Observable<any>{
    return this.http.post<any>(this.resetPasswordUrl,{token,newPassword});
  }

  getTest():Observable<any>{
    console.log('getTest() called');
    return this.http.get<any>("http://localhost:8080/api/auth/test");
  }
  

}