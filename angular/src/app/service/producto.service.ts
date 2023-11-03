import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class ProductoService {
  constructor(private http: HttpClient) { }

  cargarProductos() {
    return this.http.get('http://localhost:8080/api/v1/productos');
  }
  cargarProductoId(productoId: number){
    return this.http.get(`http://localhost:8080/api/v1/productos/${productoId}`)
  }
}

