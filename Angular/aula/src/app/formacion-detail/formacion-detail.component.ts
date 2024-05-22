import { Component, Input } from '@angular/core';
import {Formacion} from '../formacion';

@Component({
  selector: 'app-formacion-detail',
  templateUrl: './formacion-detail.component.html',
  styleUrl: './formacion-detail.component.css'
})
export class FormacionDetailComponent {

  @Input() formacion?: Formacion;

}
