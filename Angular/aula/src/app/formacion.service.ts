import { Injectable } from '@angular/core';

import { Observable, of } from 'rxjs';

import { Formacion } from './formacion';
import { FORMACIONES } from './mock-formacion';
import { MessageService } from './message.service';

@Injectable({ providedIn: 'root' })
export class FormacionService {

  constructor(private messageService: MessageService) { }

  getFormaciones(): Observable<Formacion[]> {
    const formaciones = of(FORMACIONES);
    this.messageService.add('FormacionService: fetched formaciones');
    return formaciones;
  }

  getFormacion(id: number): Observable<Formacion> {
    const formacion = FORMACIONES.find(h => h.id === id)!;
    this.messageService.add(`FormacionService: fetched formacion id=${id}`);
    return of(formacion);
  }
}
