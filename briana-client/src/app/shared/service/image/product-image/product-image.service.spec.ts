import {TestBed} from '@angular/core/testing';

import {ProductImageService} from './product-image.service';

describe('ProductImageService', () => {
  let service: ProductImageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProductImageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
