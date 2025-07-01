import { Component } from '@angular/core';
import { AttachmentsComponent } from '../../../util/attachments/attachments.component';
import { DesignComponent } from './design/design.component';
import { ImplementationComponent } from './implementation/implementation.component';

@Component({
  selector: 'app-waterfall',
  imports: [ DesignComponent, ImplementationComponent],
  templateUrl: './waterfall.component.html',
  styleUrl: './waterfall.component.css'
})
export class WaterfallComponent {

}
