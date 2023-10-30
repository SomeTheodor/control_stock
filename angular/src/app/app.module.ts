import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MovimientoComponent } from './movimiento/movimiento.component';
import { HttpClientModule } from '@angular/common/http';
import { MovimientoService } from './service/movimiento.service';
import { FormComponent } from './form/form.component';
import { FormsModule } from '@angular/forms';
import { ProductoDepositoComponent } from './producto-deposito/producto-deposito.component';
@NgModule({
  declarations: [
    AppComponent,
    MovimientoComponent,
    FormComponent,
    ProductoDepositoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [MovimientoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
