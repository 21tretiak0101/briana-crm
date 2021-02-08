import {TestBed} from '@angular/core/testing';

import {ProductCreationService} from './product-creation.service';

describe('ProductCreationService', () => {
  let service: ProductCreationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProductCreationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
