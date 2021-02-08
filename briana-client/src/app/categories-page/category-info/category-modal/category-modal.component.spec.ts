import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CategoryModalComponent} from './category-modal.component';

describe('CategoryEditModalComponent', () => {
  let component: CategoryModalComponent;
  let fixture: ComponentFixture<CategoryModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CategoryModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CategoryModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
