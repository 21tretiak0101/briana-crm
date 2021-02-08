import {Component, Input} from '@angular/core';
import {DEFAULT_AVATAR_PATH} from '../../../environments/environment';
import {ImageService} from '../../shared/service/image/image.service';
import {EmployeeImageService} from '../../shared/service/image/employee-image/employee-image.service';
import {AuthService} from '../../shared/service/auth/auth.service';

@Component({
  selector: 'app-avatar-settings',
  template: `
    <app-generic-image-handler
      [source]="avatarSrc"
      [defaultSource]="defaultSource"
      (successUploadEvent)="onSuccessUpload($event)"
      (successRemoveEvent)="onSuccessRemove()"
    ></app-generic-image-handler>
  `,
  viewProviders: [
    {provide: ImageService, useClass: EmployeeImageService}
  ]
})
export class AvatarSettingsComponent {
  @Input() avatarSrc: string;
  defaultSource = DEFAULT_AVATAR_PATH;

  constructor(private authService: AuthService) { }

  onSuccessRemove() {
    this.authService.removeImage();
  }

  onSuccessUpload(file: File) {
    this.authService.updateImage(file);
  }
}
