import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CategoriesPageComponent} from './categories-page.component';

describe('CategoriesPageComponent', () => {
  let component: CategoriesPageComponent;
  let fixture: ComponentFixture<CategoriesPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CategoriesPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CategoriesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
