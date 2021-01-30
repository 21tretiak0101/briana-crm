import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {GenericImageHandlerComponent} from './generic-image-handler.component';

describe('GenericImageHandlerComponent', () => {
  let component: GenericImageHandlerComponent;
  let fixture: ComponentFixture<GenericImageHandlerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GenericImageHandlerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GenericImageHandlerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
