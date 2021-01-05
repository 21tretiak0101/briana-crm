import {Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {TranslationToken} from '../../shared/service/language/languages';
import {EmployeeService} from '../../shared/service/employee/employee.service';
import {MaterialService} from '../../shared/service/material/material.service';
import {delay} from 'rxjs/operators';
import {DEFAULT_AVATAR_PATH} from '../../../environments/environment';

@Component({
  selector: 'app-avatar-settings',
  templateUrl: './avatar-settings.component.html',
  styleUrls: ['./avatar-settings.component.css']
})
export class AvatarSettingsComponent implements OnInit {
  @Input() l: TranslationToken;
  @ViewChild('inputElement') inputElement: ElementRef;
  imagePreview: string | ArrayBuffer;
  loading = false;
  defaultAvatarPath = DEFAULT_AVATAR_PATH;

  constructor(private employeeService: EmployeeService) { }

  ngOnInit(): void { }

  onUpload(event: Event): void {
    console.log(event.target['files'][0]);
    this.update(event.target['files'][0]);
    this.resetForm();
  }

  trigger() {
    this.inputElement.nativeElement.click();
  }

  onRemove() {
    const decision = window.confirm(this.l.rmQue as string);
    if (decision) {
      this.remove();
    }
  }

  private update(file: File): void {
    this.loading = true;
    this.employeeService.updateImage(file)
      .pipe(delay(3000))
      .subscribe(
      response => {
        console.log(response);
        this.setNew(file);
        this.loading = false;
      },
      error => {
        this.loading = false;
        MaterialService.toast(error);
      }
    );
  }

  private setNew(file: File): void {
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    };
    reader.readAsDataURL(file);
  }

  private remove() {
    this.loading = true;
    this.employeeService.removeImage()
      .pipe(delay(2000))
      .subscribe(
        response => {
          console.log(response);
          this.imagePreview = DEFAULT_AVATAR_PATH;
          this.loading = false;
        },
        error => {
          MaterialService.toast(error);
          this.loading = false;
        }
      );
  }

  private resetForm(): void {
    this.inputElement.nativeElement.value = null;
  }
}
