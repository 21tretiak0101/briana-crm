import {TestBed} from '@angular/core/testing';

import {CreationService} from './creation.service';

describe('CreationService', () => {
  let service: CreationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
