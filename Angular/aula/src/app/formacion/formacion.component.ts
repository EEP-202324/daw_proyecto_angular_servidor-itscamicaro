import { Component, OnInit } from '@angular/core';

import { Formacion } from '../formacion';
import { FormacionService } from '../formacion.service';


@Component({
  selector: 'app-formacion',
  templateUrl: './formacion.component.html',
  styleUrl: './formacion.component.css',
})
export class FormacionComponent implements OnInit {


  formaciones: Formacion[] = [];

  constructor(private formacionService: FormacionService) {}

  ngOnInit(): void {
    this.getFormaciones();
  }


getFormaciones(): void {
  this.formacionService.getFormaciones()
      .subscribe(formaciones => this.formaciones = formaciones);

}

}


