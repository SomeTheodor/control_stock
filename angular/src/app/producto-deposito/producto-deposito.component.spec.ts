import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductoDepositoComponent } from './producto-deposito.component';

describe('ProductoDepositoComponent', () => {
  let component: ProductoDepositoComponent;
  let fixture: ComponentFixture<ProductoDepositoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProductoDepositoComponent]
    });
    fixture = TestBed.createComponent(ProductoDepositoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
