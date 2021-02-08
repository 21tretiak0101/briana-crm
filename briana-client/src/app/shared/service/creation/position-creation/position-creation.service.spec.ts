import {TestBed} from '@angular/core/testing';

import {PositionCreationService} from './position-creation.service';

describe('PositionCreationService', () => {
  let service: PositionCreationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PositionCreationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
