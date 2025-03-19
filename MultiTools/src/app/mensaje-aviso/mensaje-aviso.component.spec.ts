import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MensajeAvisoComponent } from './mensaje-aviso.component';

describe('MensajeAvisoComponent', () => {
  let component: MensajeAvisoComponent;
  let fixture: ComponentFixture<MensajeAvisoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MensajeAvisoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MensajeAvisoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
