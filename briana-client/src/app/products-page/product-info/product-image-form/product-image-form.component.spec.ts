import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ProductImageFormComponent} from './product-image-form.component';

describe('ProductImageFormComponent', () => {
  let component: ProductImageFormComponent;
  let fixture: ComponentFixture<ProductImageFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductImageFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductImageFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
