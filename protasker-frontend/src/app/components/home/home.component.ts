import { Component, inject, OnInit } from '@angular/core';
import { AuthService } from '../../services/AuthService';

@Component({
  selector: 'app-home',
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{
   constructor(private authService: AuthService) {} 
  ngOnInit(): void {
    // console.log(this.authService.accessToken) 
  }

}
