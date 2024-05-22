import { Component } from '@angular/core';
import { Formacion } from '../formacion';
import {FORMACIONES} from '../mock-formacion';
import {  NgFor } from '@angular/common';
import { Form } from '@angular/forms';


@Component({
  selector: 'app-formacion',
  templateUrl: './formacion.component.html',
  styleUrl: './formacion.component.css',
})
export class FormacionComponent {

  formaciones = FORMACIONES;

  selectedFormacion?: Formacion;
onSelect(formacion: Formacion): void {
  this.selectedFormacion = formacion;
}

}


