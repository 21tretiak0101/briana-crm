import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientModalComponent } from './client-modal.component';

describe('ClientModalComponent', () => {
  let component: ClientModalComponent;
  let fixture: ComponentFixture<ClientModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
