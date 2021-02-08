import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CategoryInfoComponent} from './category-info.component';

describe('CategoryInfoComponent', () => {
  let component: CategoryInfoComponent;
  let fixture: ComponentFixture<CategoryInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CategoryInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CategoryInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
