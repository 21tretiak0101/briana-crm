import {TestBed} from '@angular/core/testing';

import {DynamicQuestionService} from './dynamic-question.service';

describe('DynamicQuestionService', () => {
  let service: DynamicQuestionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DynamicQuestionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
