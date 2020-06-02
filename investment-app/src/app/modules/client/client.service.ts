import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from "@angular/common/http";

import {  throwError, Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Client } from './client';
import { environment } from './environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private apiServer: string;
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }
  constructor(private httpClient: HttpClient) {
    this.apiServer = environment.clientApiUrl

  }


  create(Client): Observable<Client> {
    return this.httpClient.post<Client>(this.apiServer + '/save', JSON.stringify(Client), this.httpOptions)
    .pipe(
      catchError(this.errorHandler)
    )
  }

  update(Client): Observable<Client> {
    return this.httpClient.put<Client>(this.apiServer + '/update', JSON.stringify(Client), this.httpOptions)
    .pipe(
      catchError(this.errorHandler)
    )
  }
  getById(id): Observable<Client> {
    return this.httpClient.get<Client>(this.apiServer + '/' + id)
    .pipe(
      catchError(this.errorHandler)
    )
  }

  getAll(): Observable<Client[]> {
    return this.httpClient.get<Client[]>(this.apiServer + '/all')
    .pipe(
      catchError(this.errorHandler)
    )
  }


  delete(id){
    console.log('in delete')
    return this.httpClient.delete<Client>(this.apiServer + '/' + id, this.httpOptions)
    .pipe(
      catchError(this.errorHandler)
    )
  }
  errorHandler(error) {
     let errorMessage = '';
     if(error.error instanceof ErrorEvent) {
       // Get client-side error
       errorMessage = error.error.message;
     } else {
       // Get server-side error
       errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
     }
     console.log(errorMessage);
     return throwError(errorMessage);
  }
}

