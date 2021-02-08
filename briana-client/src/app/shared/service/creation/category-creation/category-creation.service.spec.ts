import {TestBed} from '@angular/core/testing';

import {CategoryCreationService} from './category-creation.service';

describe('CategoryCreationService', () => {
  let service: CategoryCreationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CategoryCreationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
