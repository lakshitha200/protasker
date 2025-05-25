import { inject, Injectable } from "@angular/core";
import { environment } from "../environments/environment";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { Router } from "@angular/router";


@Injectable({
  providedIn: 'root'
})
export class UserService {
    private http = inject(HttpClient);
    private router = inject(Router);
    private readonly updateUserUrl = environment.updateUserUrl;

    updateUser(userId: string,userData:any): Observable<any> {
      return this.http.put<any>(this.updateUserUrl+"/"+userId, userData);
    }

    updateUserProfilePicture(userId: string,formData:FormData): Observable<any> {
      return this.http.put<any>(this.updateUserUrl+"/"+userId+"/profile-picture", formData);
    }
}