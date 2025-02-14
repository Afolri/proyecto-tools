import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganizadorComputadorasComponent } from './organizador-computadoras.component';

describe('OrganizadorComputadorasComponent', () => {
  let component: OrganizadorComputadorasComponent;
  let fixture: ComponentFixture<OrganizadorComputadorasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OrganizadorComputadorasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OrganizadorComputadorasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
