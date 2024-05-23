import { Component, OnInit } from '@angular/core';

import { Formacion } from '../formacion';
import { FormacionService } from '../formacion.service';
import { MessageService } from '../message.service';


@Component({
  selector: 'app-formacion',
  templateUrl: './formacion.component.html',
  styleUrl: './formacion.component.css',
})
export class FormacionComponent implements OnInit {

  selectedFormacion?: Formacion;

  formaciones: Formacion[] = [];

  constructor(private formacionService: FormacionService, private messageService: MessageService) {}

  ngOnInit(): void {
    this.getFormaciones();
  }

onSelect(formacion: Formacion): void {
  this.selectedFormacion = formacion;
  this.messageService.add(`FormacionesComponent: Selected formacion id=${formacion.id}`);
}

getFormaciones(): void {
  this.formacionService.getFormacion()
      .subscribe(formaciones => this.formaciones = formaciones);

}

}


