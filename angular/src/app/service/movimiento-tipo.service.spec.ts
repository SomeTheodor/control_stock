import { TestBed } from '@angular/core/testing';

import { MovimientoTipoService } from './movimiento-tipo.service';

describe('MovimientoTipoService', () => {
  let service: MovimientoTipoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MovimientoTipoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
