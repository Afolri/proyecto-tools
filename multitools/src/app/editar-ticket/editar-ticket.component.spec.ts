import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarTicketComponent } from './editar-ticket.component';

describe('EditarTicketComponent', () => {
  let component: EditarTicketComponent;
  let fixture: ComponentFixture<EditarTicketComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditarTicketComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditarTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
