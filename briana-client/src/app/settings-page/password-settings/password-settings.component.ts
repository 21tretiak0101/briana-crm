import {Component, Input, OnInit} from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  ValidatorFn,
  Validators
} from '@angular/forms';
import {LanguageTokens} from '../../shared/service/language/languages';

@Component({
  selector: 'app-password-settings',
  templateUrl: './password-settings.component.html'
})
export class PasswordSettingsComponent implements OnInit {
  @Input() tokens: LanguageTokens;
  form: FormGroup;

  constructor() { }

  ngOnInit(): void {
    this.initForm();
  }

  initForm(): void {
    this.form = new FormGroup({
      oldPassword: new FormControl(),
      newPassword: new FormControl('', [Validators.minLength(6)])
    });
    this.form.addControl('confirmPassword', new FormControl('',
      this.matchValue('newPassword', this.form)
      )
    );
  }

  matchValue(passwordKey: string, form: FormGroup): ValidatorFn {
    return (control: AbstractControl): {[key: string]: any} | null => {
      const repeatPassword = control.value;
      const match = form.value[passwordKey] === repeatPassword;
      return !match ? {forbiddenName: {value: control.value}} : null;
    };
  }
}
