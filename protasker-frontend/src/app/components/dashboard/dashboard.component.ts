import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/AuthService';

@Component({
  selector: 'app-dashboard',
  imports: [],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit{
  constructor(private authService:AuthService ){}

  ngOnInit(): void {
    this.authService.getCurrentUser().subscribe({
      next: response => {
        console.log(response);
      },
      error: err => {
        console.log('Rediret failed!'+ err);
      }
    })
  }


}
