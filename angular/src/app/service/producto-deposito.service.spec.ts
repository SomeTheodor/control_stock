import { TestBed } from '@angular/core/testing';

import { ProductoDepositoService } from './producto-deposito.service';

describe('ProductoDepositoService', () => {
  let service: ProductoDepositoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProductoDepositoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
