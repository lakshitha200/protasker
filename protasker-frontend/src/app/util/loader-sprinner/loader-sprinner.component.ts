import { Component } from '@angular/core';
import { LoaderService, LoaderState } from '../../services/Loader.service';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-loader-sprinner',
  imports: [CommonModule],
  templateUrl: './loader-sprinner.component.html',
  styleUrl: './loader-sprinner.component.css'
})
export class LoaderSprinnerComponent {
 loaderState$: Observable<LoaderState>;
  
  constructor(private loaderService: LoaderService) {
    this.loaderState$ = this.loaderService.loaderState$;
  }
}
