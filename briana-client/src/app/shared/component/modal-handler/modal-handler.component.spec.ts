import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ModalHandlerComponent} from './modal-handler.component';

describe('ModalHandlerComponent', () => {
  let component: ModalHandlerComponent;
  let fixture: ComponentFixture<ModalHandlerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModalHandlerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalHandlerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
