import {TestBed} from '@angular/core/testing';

import {EmployeeImageService} from './employee-image.service';

describe('EmployeeImageService', () => {
  let service: EmployeeImageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmployeeImageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
