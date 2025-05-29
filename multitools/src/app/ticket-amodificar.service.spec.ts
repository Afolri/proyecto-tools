import { TestBed } from '@angular/core/testing';

import { TicketAModificarService } from './ticket-amodificar.service';

describe('TicketAModificarService', () => {
  let service: TicketAModificarService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TicketAModificarService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
