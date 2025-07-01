import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { ChartsComponent } from '../../util/charts/charts.component';

@Component({
  selector: 'app-project',
  imports: [RouterOutlet,CommonModule],
  templateUrl: './project.component.html',
  styleUrl: './project.component.css'
})
export class ProjectComponent implements OnInit{

  showProjectPage = false;
  constructor(private router:Router){}

  ngOnInit(): void {
    if(this.router.url === '/project'){
        this.showProjectPage = true;
    }
  }



}
