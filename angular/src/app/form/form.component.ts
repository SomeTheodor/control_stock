import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ProductoService } from '../service/producto.service';
import { MovimientoTipoService } from '../service/movimiento-tipo.service';
import { DepositoService } from '../service/deposito.service';
import { ProductoDepositoService } from '../service/producto-deposito.service';
import { MovimientoService } from '../service/movimiento.service';
import { DatosFormulario } from '../datos-formulario';
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
  cantidadProducto: number = 0;
  cantidadDeposito: any[] = [];

  constructor(private http: HttpClient, private productoService: ProductoService, private movimientoTipoService: MovimientoTipoService,
    private depositoService: DepositoService, private productoDepositoService: ProductoDepositoService, private movimientoService: MovimientoService) {}

  ngOnInit() {
    this.cargarProductos();
    this.cargarTiposMovimiento();
    this.cargarDepositos();
    this.cargarCantidadProducto();
  }

  cargarProductos() {
    this.productoService.cargarProductos().subscribe(
      (response: any) => {
        this.productos = response;
      },
      (error) => {
        this.errorMensaje = 'Error al cargar opciones de productos: ' + error;
      }
    );
  }

  cargarTiposMovimiento() {
    this.movimientoTipoService.cargarTiposMovimiento().subscribe(
      (response: any) => {
        this.tiposMovimiento = response;
      },
      (error) => {
        this.errorMensaje = 'Error al cargar opciones de tipos de movimiento: ' + error;
      }
    );
  }

  cargarDepositos() {
    this.depositoService.cargarDeposito().subscribe(
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
    this.productoService.cargarProductoId(productoId).subscribe(
      (response: any) => {
        this.cantidadProducto = response.cantidad;
      },
      (error) => {
        console.error('Error al cargar la cantidad del producto', error);
      }
    );

    // Obtener la lista de depósitos y sus cantidades respectivas
    this.productoDepositoService.cargarProductoDeposito(productoId).subscribe(
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
  
    this.movimientoService.enviarDatos(requestData).subscribe(
      (response) => {
        this.errorMensaje = 'Datos enviados con éxito';
      },
      (error) => {
        this.errorMensaje = 'Error al enviar los datos';
      });
    console.log('Datos a enviar:', requestData);
    console.log(this.cantidadDeposito);

    setTimeout(() => {
      window.location.reload();
  }, 1000);
  }
  
}  
