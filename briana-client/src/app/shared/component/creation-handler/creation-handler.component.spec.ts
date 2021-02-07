import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CreationHandlerComponent} from './creation-handler.component';

describe('CreationHandlerComponent', () => {
  let component: CreationHandlerComponent;
  let fixture: ComponentFixture<CreationHandlerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreationHandlerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreationHandlerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
