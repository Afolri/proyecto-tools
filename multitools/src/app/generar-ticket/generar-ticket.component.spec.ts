import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenerarTicketComponent } from './generar-ticket.component';

describe('GenerarTicketComponent', () => {
  let component: GenerarTicketComponent;
  let fixture: ComponentFixture<GenerarTicketComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GenerarTicketComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GenerarTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
