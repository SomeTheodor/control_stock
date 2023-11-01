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
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {
  formularioData: DatosFormulario = { cantidad: 0, productoId: 0, movimientoTipoId: 0, depositoId: 0 };
  productos: any[] = [];
  tiposMovimiento: any[] = [];
  depositos: any[] = [];
  errorMensaje: string = '';
  cantidadProducto: number = 0; // Cambiado a número en lugar de array
  cantidadDeposito: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.cargarProductos();
    this.cargarTiposMovimiento();
    this.cargarDepositos();
  }

  cargarProductos() {
    this.http.get('http://localhost:8080/api/v1/productos').subscribe(
      (response: any) => {
        this.productos = response;
      },
      (error) => {
        this.errorMensaje = 'Error al cargar opciones de productos: ' + error;
      }
    );
  }

  cargarTiposMovimiento() {
    this.http.get('http://localhost:8080/api/v1/movimientos_tipos').subscribe(
      (response: any) => {
        this.tiposMovimiento = response;
      },
      (error) => {
        this.errorMensaje = 'Error al cargar opciones de tipos de movimiento: ' + error;
      }
    );
  }

  cargarDepositos() {
    this.http.get('http://localhost:8080/api/v1/depositos').subscribe(
      (response: any) => {
        this.depositos = response;
      },
      (error) => {
        this.errorMensaje = 'Error al cargar opciones de depósitos: ' + error;
      }
    );
  }

  cargarCantidadProducto() {
    const productoId = this.formularioData.productoId;
    this.http.get(`http://localhost:8080/api/v1/productos/${productoId}`).subscribe(
      (response: any) => {
        this.cantidadProducto = response.cantidad;
      },
      (error) => {
        console.error('Error al cargar la cantidad del producto', error);
      }
    );

    // Obtener la lista de depósitos y sus cantidades respectivas
    this.http.get(`http://localhost:8080/api/v1/productos_depositos/producto/${productoId}`).subscribe(
      (response: any) => {
        this.cantidadDeposito = response;
      },
      (error) => {
        console.error('Error al cargar la lista de depósitos', error);
      }
    );
  }

  enviarDatos() {
    const requestData = {
      producto: { id: this.formularioData['productoId'] },
      deposito: { id: this.formularioData['depositoId'] },
      movimientoTipo: { id: this.formularioData['movimientoTipoId'] },
      cantidad: this.formularioData['cantidad']
    };
    const url = 'http://localhost:8080/api/v1/movimientos';
  
    this.http.post(url, requestData).subscribe(
      (response) => {
        this.errorMensaje = 'Datos enviados con éxito';
      },
      (error) => {
        this.errorMensaje = 'Error al enviar los datos';
      });
    console.log('Datos a enviar:', requestData);
    console.log(this.cantidadDeposito);
  }
}  
