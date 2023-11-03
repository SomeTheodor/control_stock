import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MovimientoTipoService {
  constructor(private http: HttpClient) { }

  cargarTiposMovimiento() {
    return this.http.get('http://localhost:8080/api/v1/movimientos_tipos');
  }
}
