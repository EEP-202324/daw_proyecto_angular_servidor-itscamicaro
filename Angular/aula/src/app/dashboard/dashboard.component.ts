import { Component, OnInit } from '@angular/core';
import { Formacion } from '../formacion';
import { FormacionService } from '../formacion.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {
  formaciones: Formacion[] = [];

  constructor(private formacionService: FormacionService) { }

  ngOnInit(): void {
    this.getFormaciones();
  }

  getFormaciones(): void {
    this.formacionService.getFormaciones()
      .subscribe(formaciones => this.formaciones = formaciones.slice(1, 5));
  }
}
