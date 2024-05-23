import { Component, OnInit } from '@angular/core';

import { Observable, Subject } from 'rxjs';

import {
  debounceTime, distinctUntilChanged, switchMap
} from 'rxjs/operators';

import { Formacion } from '../formacion';
import { FormacionService } from '../formacion.service';

@Component({
  selector: 'app-formacion-search',
  templateUrl: './formacion-search.component.html',
  styleUrl: './formacion-search.component.css'
})
export class FormacionSearchComponent implements OnInit {
  formaciones$!: Observable<Formacion[]>;
  private searchTerms = new Subject<string>();

  constructor(private formacionService: FormacionService) {}


    // Push a search term into the observable stream.
    search(term: string): void {
      this.searchTerms.next(term);
    }

    ngOnInit(): void {
      this.formaciones$ = this.searchTerms.pipe(
        // wait 300ms after each keystroke before considering the term
        debounceTime(300),

        // ignore new term if same as previous term
        distinctUntilChanged(),

        // switch to new search observable each time the term changes
        switchMap((term: string) => this.formacionService.searchFormaciones(term)),
      );
    }
}
