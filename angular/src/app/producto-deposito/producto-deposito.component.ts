import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

interface DatosFormulario {
  cantidad: number;
  productoId: number;
  depositoId: number;
}

@Component({
  selector: 'app-form2',
  templateUrl: './producto-deposito.component.html',
})
export class ProductoDepositoComponent implements OnInit {
  formularioData: DatosFormulario = { cantidad: 0, productoId: 0, depositoId: 0 };
  productos: any[] = []; // Array para almacenar opciones de productos
  depositos: any[] = []; // Array para almacenar opciones de depósitos

  constructor(private http: HttpClient) {}

  ngOnInit() {
    // Carga las opciones de productos, tipos de movimiento y depósitos desde tu backend.
    this.cargarProductos();
    this.cargarDepositos();
  }

  cargarProductos() {
    this.http.get('http://localhost:8080/api/v1/productos').subscribe(
      (response: any) => {
        this.productos = response; // Asigna la respuesta a this.productos
      },
      (error) => {
        console.error('Error al cargar opciones de productos', error);
      }
    );
  }
  cargarDepositos() {
    this.http.get('http://localhost:8080/api/v1/depositos').subscribe(
      (response: any) => {
        this.depositos = response; // Asigna la respuesta a this.depositos
      },
      (error) => {
        console.error('Error al cargar opciones de depósitos', error);
      }
    );
  }

  enviarDatos() {
    const requestData = {
      producto: { id: this.formularioData.productoId },
      deposito: { id: this.formularioData.depositoId }
    };
    const url = 'http://localhost:8080/api/v1/productos_depositos';

    this.http.post(url, requestData).subscribe(
      (response) => {
        console.log('Datos enviados con éxito', response);
      },
      (error) => {
        console.error('Error al enviar los datos', error);
      }
    );
    console.log('Datos a enviar:', requestData);
  }
}
