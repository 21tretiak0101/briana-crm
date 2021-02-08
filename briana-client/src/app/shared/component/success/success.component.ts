import {Component, Input} from '@angular/core';

const DEFAULT_TIMEOUT = 2000;

@Component({
  selector: 'app-success',
  styles: ['.success {color: green; font-size: 35px}'],
  template: `
    <i
      class="material-icons success"
      *ngIf="isSuccess"
    >
      check_circle_outline
    </i>
  `
})
export class SuccessComponent {
  @Input() timeout: number = DEFAULT_TIMEOUT;
  isSuccess = false;

  success(onSuccess?: () => void): void {
    this.isSuccess = true;
    setTimeout(() => {
      this.isSuccess = false;
      onSuccess?.();
    }, this.timeout);
  }
}
