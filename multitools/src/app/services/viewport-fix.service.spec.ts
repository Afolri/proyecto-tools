import { TestBed } from '@angular/core/testing';

import { ViewportFixService } from './viewport-fix.service';

describe('ViewportFixService', () => {
  let service: ViewportFixService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ViewportFixService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
