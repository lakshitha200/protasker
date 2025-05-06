import { Component } from '@angular/core';
import { ModalService } from '../../services/Model.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-model',
  imports: [CommonModule],
  templateUrl: './model.component.html',
  styleUrl: './model.component.css'
})
export class ModelComponent {
  constructor(public modal: ModalService) {

  }
}
