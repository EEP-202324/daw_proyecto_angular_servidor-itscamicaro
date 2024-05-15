import { Component } from '@angular/core';
import { AulaLocationComponent } from '../aula-location/aula-location.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-aula',
  standalone: true,
  imports: [
    CommonModule,
    AulaLocationComponent
  ],
  template: `
  <section>
    <form>
      <input type="text" placeholder="Filter by city">
      <button class="primary" type="button">Search</button>
    </form>
  </section>
  <section class="results">
    <app-aula-location></app-aula-location>
  </section>
  `,
  styleUrl: './aula.component.css'
})
export class AulaComponent {

}
