import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import {Formacion} from '../formacion';
import { FormacionService } from '../formacion.service';

@Component({
  selector: 'app-formacion-detail',
  templateUrl: './formacion-detail.component.html',
  styleUrl: './formacion-detail.component.css'
})
export class FormacionDetailComponent implements OnInit {
  formacion: Formacion | undefined;

  constructor(
    private route: ActivatedRoute,
    private formacionService: FormacionService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getFormacion();
  }

  getFormacion(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.formacionService.getFormacion(id)
      .subscribe(formacion => this.formacion = formacion);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    if (this.formacion) {
      this.formacionService.updateFormacion(this.formacion)
        .subscribe(() => this.goBack());
    }
  }

}
