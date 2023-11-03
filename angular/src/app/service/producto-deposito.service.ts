import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class ProductoDepositoService {
  constructor(private http: HttpClient) { }

  cargarProductoDeposito(productoId: number) {
    return this.http.get(`http://localhost:8080/api/v1/productos_depositos/producto/${productoId}`);
  }
}
