import {TestBed} from '@angular/core/testing';

import {ClientCreationService} from './client-creation.service';

describe('ClientCreationService', () => {
  let service: ClientCreationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClientCreationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
