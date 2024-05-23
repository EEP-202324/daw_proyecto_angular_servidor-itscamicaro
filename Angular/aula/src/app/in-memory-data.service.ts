import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';
import {Formacion } from './formacion';

@Injectable({
  providedIn: 'root'
})
export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    const formaciones = [
      { id: 1, nombre: 'Administración y finanzas', precio: '1.500€', dual: true, centro: 'Formación Azuqueca de Henares' },

     { id: 2, nombre: 'Comercio internacional', precio: '1.600€', dual: true, centro: 'Formación Alcalá de Henares' },

    { id: 3, nombre: 'Desarrollo de aplicaciones web', precio: '2.500€', dual: true, centro: 'Formación Majadahonda' },

    { id: 4, nombre: 'Desarrollo de aplicaciones multiplataforma ', precio: '2.500 €', dual: true, centro: 'Formación Majadahonda' },

    { id: 5, nombre: 'Marketing y publicidad', precio: '1.800€', dual: true, centro: 'Formación Azuqueca de Henares' },

    { id: 6, nombre: 'Higiene bucodental', precio: '3.100€', dual: true, centro: 'Formación Alcalá de Henares' },

    { id: 7, nombre: 'Nutrición y dietética', precio: '2.300€', dual: true, centro: 'Formación Alcalá de Henares' },

    { id: 8, nombre: 'Integración social', precio: '800€', dual: true, centro: 'Formación Majadahonda' }

    ];
    return {formaciones};

}

genId(formaciones: Formacion[]): number {
  return formaciones.length > 0 ? Math.max(...formaciones.map(formacion => formacion.id)) + 1 : 11;
  }
}
