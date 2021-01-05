import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AvatarSettingsComponent } from './avatar-settings.component';

describe('AvatarSettingsComponent', () => {
  let component: AvatarSettingsComponent;
  let fixture: ComponentFixture<AvatarSettingsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AvatarSettingsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AvatarSettingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
