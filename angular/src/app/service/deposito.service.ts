import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DepositoService {
  constructor(private http: HttpClient) { }

  cargarDeposito() {
    return this.http.get('http://localhost:8080/api/v1/depositos');
  }
}
