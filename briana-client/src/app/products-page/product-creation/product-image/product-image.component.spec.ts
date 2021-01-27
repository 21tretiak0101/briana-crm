import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ProductImageComponent} from './product-image.component';

describe('ProductImageComponent', () => {
  let component: ProductImageComponent;
  let fixture: ComponentFixture<ProductImageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductImageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductImageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
