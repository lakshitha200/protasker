import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/AuthService';
import { BehaviorSubject } from 'rxjs';
import { ChartsComponent } from '../../util/charts/charts.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  imports: [ChartsComponent, CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit{


  taskStatus = {
        labels: ['E-commerce PD', 'EMS', 'Test Project A'],
        datasets: [
          { label: 'To Do', data: [37, 35, 23], backgroundColor: '#EF4444' },
          { label: 'In Progress', data: [34, 34, 26], backgroundColor: '#F59E0B' },
          { label: 'Completed', data: [21, 23, 54], backgroundColor: '#10B981' }
        ]
      }
  taskStatusOption = { 
    responsive: true,
    maintainAspectRatio: false,
    scales: { 
      x: { 
        stacked: true,
      //    ticks: { color: 'white' }, // X-axis text color
      // grid: { color: '#3f3f3f' }, // Light gray grid lines (X-axis)
      }, 
      y: { 
        stacked: true,
      //         ticks: { color: 'white' }, // Y-axis text color
      // grid: { color: '#3f3f3f' }, // Light gray grid lines (X-axis)
      } 
    }
  //    plugins: {
  //   legend: {
  //     labels: { color: 'white' }, // Legend text color
  //   },
  //   title: {
  //     color: 'white', // Chart title color
  //   }
  // } 
  }
  
  projectProgressData = {
        labels: ['E-commerce PD', 'EMS', 'Test Project C'],
        datasets: [{
          label: 'Completion (%)',
          data: [20, 55, 60],
          backgroundColor: ['#3f37c9', '#4cc9f0', '#4895ef']
        }]
      
  }

  teamTaskdata= {
        labels: ['Lakshitha', 'Himash', 'Sithum','Ashen', 'Dewni', 'Gauravi','Yasith', 'Sadeesh'],
        datasets: [{
          label: 'Hours Assigned',
          data: [50,25,20,40, 15, 33,28, 30],
          backgroundColor: ['#3B82F6', '#10B981', '#EF4444','#8B5CF6', '#F97316', '#14B8A6','#EC4899','#F59E0B']
        }]
      }

  
  
  private currentUserSubject = new BehaviorSubject<any>(null);
  public currentUser$ = this.currentUserSubject.asObservable();

  constructor(private authService:AuthService ){}

  ngOnInit(): void {
   this.authService.fetchCurrentUser().subscribe();
  }

  
}
