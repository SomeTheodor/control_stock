import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MovimientoService {
  private baseUrl = 'http://localhost:8080/api/v1/movimientos';
  
  public get(url: string): Observable<any> {
    return this.http.get(url); // GET
  }
  constructor(private http: HttpClient) { }

  enviarDatos(data: any): Observable<any> {
    const url = this.baseUrl;
    return this.http.post(url, data);
  } 
  
}