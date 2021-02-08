import {TestBed} from '@angular/core/testing';

import {EmployeeCreationService} from './employee-creation.service';

describe('EmployeeCreationService', () => {
  let service: EmployeeCreationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmployeeCreationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
