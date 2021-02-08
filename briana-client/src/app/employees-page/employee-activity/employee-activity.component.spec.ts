import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {EmployeeActivityComponent} from './employee-activity.component';

describe('EmployeeActivityComponent', () => {
  let component: EmployeeActivityComponent;
  let fixture: ComponentFixture<EmployeeActivityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeActivityComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeActivityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
