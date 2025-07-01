import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-project-create',
  imports: [RouterOutlet,CommonModule],
  templateUrl: './project-create.component.html',
  styleUrl: './project-create.component.css'
})
export class ProjectCreateComponent implements OnInit{
 showProjectCreatePage = false;
  constructor(private router:Router){}

  ngOnInit(): void {
    if(this.router.url === '/project/project-create'){
        this.showProjectCreatePage = true;
    }
  }
}
