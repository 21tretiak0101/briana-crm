import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {EmittingImageHandlerComponent} from './emitting-image-handler.component';

describe('EmittingImageHandlerComponent', () => {
  let component: EmittingImageHandlerComponent;
  let fixture: ComponentFixture<EmittingImageHandlerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmittingImageHandlerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmittingImageHandlerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
