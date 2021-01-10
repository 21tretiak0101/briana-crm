import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientFilterComponent } from './client-filter.component';

describe('ClientFilterComponent', () => {
  let component: ClientFilterComponent;
  let fixture: ComponentFixture<ClientFilterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientFilterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
