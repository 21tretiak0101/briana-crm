import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {EmployeeCreationComponent} from './employee-creation.component';

describe('EmployeeCreationComponent', () => {
  let component: EmployeeCreationComponent;
  let fixture: ComponentFixture<EmployeeCreationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeCreationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
