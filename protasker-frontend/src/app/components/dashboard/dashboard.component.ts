import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/AuthService';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-dashboard',
  imports: [],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit{

  private currentUserSubject = new BehaviorSubject<any>(null);
  public currentUser$ = this.currentUserSubject.asObservable();

  constructor(private authService:AuthService ){}

  ngOnInit(): void {
   this.authService.fetchCurrentUser().subscribe();
  }

  
}
