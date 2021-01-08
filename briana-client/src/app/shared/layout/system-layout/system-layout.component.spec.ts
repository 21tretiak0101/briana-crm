import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SystemLayoutComponent } from './system-layout.component';

describe('SystemLayoutComponent', () => {
  let component: SystemLayoutComponent;
  let fixture: ComponentFixture<SystemLayoutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SystemLayoutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SystemLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
