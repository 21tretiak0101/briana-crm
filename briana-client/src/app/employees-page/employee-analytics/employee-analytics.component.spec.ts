import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {EmployeeAnalyticsComponent} from './employee-analytics.component';

describe('EmployeeAnalyticsComponent', () => {
  let component: EmployeeAnalyticsComponent;
  let fixture: ComponentFixture<EmployeeAnalyticsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeAnalyticsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeAnalyticsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
