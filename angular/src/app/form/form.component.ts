import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

interface DatosFormulario {
  cantidad: number;
  productoId: number;
  movimientoTipoId: number;
  depositoId: number;
}

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
})
export class FormComponent implements OnInit {
  formularioData: DatosFormulario = { cantidad: 0, productoId: 0, movimientoTipoId: 0, depositoId: 0 };
  productos: any[] = []; // Array para almacenar opciones de productos
  tiposMovimiento: any[] = []; // Array para almacenar opciones de tipos de movimiento
  depositos: any[] = []; // Array para almacenar opciones de depósitos

  constructor(private http: HttpClient) {}

  ngOnInit() {
    // Carga las opciones de productos, tipos de movimiento y depósitos desde tu backend.
    this.cargarProductos();
    this.cargarTiposMovimiento();
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

  cargarTiposMovimiento() {
    this.http.get('http://localhost:8080/api/v1/movimientos_tipos').subscribe(
      (response: any) => {
        this.tiposMovimiento = response; // Asigna la respuesta a this.tiposMovimiento
      },
      (error) => {
        console.error('Error al cargar opciones de tipos de movimiento', error);
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
      deposito: { id: this.formularioData.depositoId },
      movimientoTipo: { id: this.formularioData.movimientoTipoId},
      cantidad: this.formularioData.cantidad
    };
    const url = 'http://localhost:8080/api/v1/movimientos';

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
