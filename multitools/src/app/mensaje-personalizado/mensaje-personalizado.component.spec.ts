import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MensajePersonalizadoComponent } from './mensaje-personalizado.component';

describe('MensajePersonalizadoComponent', () => {
  let component: MensajePersonalizadoComponent;
  let fixture: ComponentFixture<MensajePersonalizadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MensajePersonalizadoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MensajePersonalizadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
