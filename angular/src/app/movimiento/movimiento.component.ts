import { Component, OnInit } from '@angular/core';
import { MovimientoService } from '../service/movimiento.service';
@Component({
  selector: 'app-movimiento',
  templateUrl: './movimiento.component.html',
  styleUrls: ['./movimiento.component.css']
})
export class MovimientoComponent implements OnInit {
  public listaCard:any[] = [];

  constructor(private MovimientoService: MovimientoService) {}

  ngOnInit(): void {
    this.cargarDato();
    
  }

  public cargarDato() {
    this.MovimientoService.get("http://localhost:8080/api/v1/movimientos")
  .subscribe(
    respuesta => {
      console.log(respuesta);
      this.listaCard = respuesta;
    },
    error => {
      console.error("Error en la solicitud:", error);
    }
  );

  }
}