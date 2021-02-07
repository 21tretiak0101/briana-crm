import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ClientCreationComponent} from './client-creation.component';

describe('ClientCreationComponent', () => {
  let component: ClientCreationComponent;
  let fixture: ComponentFixture<ClientCreationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientCreationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
