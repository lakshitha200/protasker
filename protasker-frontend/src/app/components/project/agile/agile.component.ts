import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ProductBacklogComponent } from './product-backlog/product-backlog.component';

@Component({
  selector: 'app-agile',
  imports: [CommonModule,ProductBacklogComponent],
  templateUrl: './agile.component.html',
  styleUrl: './agile.component.css'
})
export class AgileComponent {

}
