import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ChartsComponent } from '../../../util/charts/charts.component';

@Component({
  selector: 'app-project-details',
  imports: [CommonModule,ChartsComponent],
  templateUrl: './project-details.component.html',
  styleUrl: './project-details.component.css'
})
export class ProjectDetailsComponent {

  projectStatusdata= {
        labels: ['To Do (37)', 'In Progress (35)', 'Done (23)'],
        datasets: [{
          data: [29, 35, 23],
          backgroundColor: ['#ff9f40', '#36a2eb', '#4bc0c0']
        }]
      }

  budgetData = {
        labels: ['Estimated', 'Actual'],
        datasets: [{
          label: 'Budget ($)',
          data: [5000, 5700],
          backgroundColor: ['#4cc9f0', '#4361ee']
        }]
      }
  
  phaseData = {
    labels: ['Requirements', 'Design', 'Implementation', 'Testing', 'Deployment','Maintenance'],
    datasets: [{
      label: 'Completion %',
      data: [100, 20, 0, 0, 0,0],
      backgroundColor: [
        '#4caf50', '#2196f3', '#ff9800', '#9c27b0', '#f44336', '#888888'
      ],
      borderRadius: 5
    }]
  }

  teamTaskdata= {
         labels: ['Lakshitha', 'Himash', 'Sithum','Ashen', 'Dewni', 'Gauravi','Yasith', 'Sadeesh'],
        datasets: [{
          label: 'Tasks Assigned',
          data: [12, 8, 4,5, 4, 4,9, 8],
          backgroundColor: ['#3B82F6', '#10B981', '#EF4444','#8B5CF6', '#F97316', '#14B8A6','#EC4899','#F59E0B']
        }]
      }

  phaseOptions = {
    indexAxis: 'y',
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: { display: false },
      title: {
        display: true,
        text: 'Phase Completion'
      }
    },
    scales: {
      x: {
        max: 100,
        ticks: {
          callback: (value: any) => `${value}%`
        }
      }
    }
  }

}
