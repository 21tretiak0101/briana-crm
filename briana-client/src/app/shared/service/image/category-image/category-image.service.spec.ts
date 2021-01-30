import {TestBed} from '@angular/core/testing';

import {CategoryImageService} from './category-image.service';

describe('CategoryImageService', () => {
  let service: CategoryImageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CategoryImageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
