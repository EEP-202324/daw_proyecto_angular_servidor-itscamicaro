import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Formacion } from './formacion';
import { MessageService } from './message.service';

@Injectable({ providedIn: 'root' })
export class FormacionService {
  private formacionesUrl = 'api/formaciones';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  /** GET heroes from the server */
  getFormaciones(): Observable<Formacion[]> {
  return this.http.get<Formacion[]>(this.formacionesUrl)
    .pipe(
      tap(_ => this.log('fetched formaciones')),
      catchError(this.handleError<Formacion[]>('getFormaciones', []))
    );
  }

    /** GET hero by id. Return `undefined` when id not found */
  getHeroNo404<Data>(id: number): Observable<Formacion> {
    const url = `${this.formacionesUrl}/?id=${id}`;
      return this.http.get<Formacion[]>(url)
        .pipe(
          map(formaciones => formaciones[0]), // returns a {0|1} element array
          tap(h => {
            const outcome = h ? 'fetched' : 'did not find';
            this.log(`${outcome} formacion id=${id}`);
          }),
          catchError(this.handleError<Formacion>(`getFormacion id=${id}`))
        );
    }

  /** GET hero by id. Will 404 if id not found */
  getFormacion(id: number): Observable<Formacion> {
    const url = `${this.formacionesUrl}/${id}`;
    return this.http.get<Formacion>(url).pipe(
      tap(_ => this.log(`fetched formaciones id=${id}`)),
      catchError(this.handleError<Formacion>(`getFormacion id=${id}`))
    );
  }

/* GET heroes whose name contains search term */
  searchFormaciones(term: string): Observable<Formacion[]> {
    if (!term.trim()) {
      // if not search term, return empty hero array.
    return of([]);
  }
  return this.http.get<Formacion[]>(`${this.formacionesUrl}/?name=${term}`).pipe(
    tap(x => x.length ?
       this.log(`found formaciones matching "${term}"`) :
       this.log(`no formaciones matching "${term}"`)),
    catchError(this.handleError<Formacion[]>('searchFormaciones', []))
  );
}
  /** POST: add a new hero to the server */
  addFormacion(formacion: Formacion): Observable<Formacion> {
    return this.http.post<Formacion>(this.formacionesUrl, formacion, this.httpOptions).pipe(
      tap((newFormacion: Formacion) => this.log(`added formacion w/ id=${newFormacion.id}`)),
      catchError(this.handleError<Formacion>('addFormacion'))
    );
  }
  /** DELETE: delete the hero from the server */
  deleteFormacion(id: number): Observable<Formacion> {
    const url = `${this.formacionesUrl}/${id}`;

    return this.http.delete<Formacion>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted formacion id=${id}`)),
      catchError(this.handleError<Formacion>('deleteFormacion'))
  );
}
/** PUT: update the hero on the server */
  updateFormacion(formacion: Formacion): Observable<any> {
    return this.http.put(this.formacionesUrl, formacion, this.httpOptions).pipe(
      tap(_ => this.log(`updated hero id=${formacion.id}`)),
      catchError(this.handleError<any>('updateFormacion'))
    );
  }
  /**
 * Handle Http operation that failed.
 * Let the app continue.
 *
 * @param operation - name of the operation that failed
 * @param result - optional value to return as the observable result
 */
private handleError<T>(operation = 'operation', result?: T) {
  return (error: any): Observable<T> => {

    // TODO: send the error to remote logging infrastructure
    console.error(error); // log to console instead

    // TODO: better job of transforming error for user consumption
    this.log(`${operation} failed: ${error.message}`);

    // Let the app keep running by returning an empty result.
    return of(result as T);
  };
}

  private log(message: string) {
    this.messageService.add(`FormacionService: ${message}`);
  }
}
