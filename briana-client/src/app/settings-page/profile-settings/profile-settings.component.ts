import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {LanguageTokens} from '../../shared/service/language/languages';
import {AuthService} from '../../shared/service/auth/auth.service';
import {Employee} from '../../shared/entities';
import {EmployeeService} from '../../shared/service/employee/employee.service';
import {MaterialService} from '../../shared/service/material/material.service';
import {SuccessComponent} from '../../shared/component/success/success.component';
import {ResponsiveCircularLoaderComponent} from '../../shared/component/circular-loader/responsive-circular-loader/responsive-circular-loader.component';

@Component({
  selector: 'app-profile-settings',
  templateUrl: './profile-settings.component.html',
  styleUrls: ['./profile-settings.component.css']
})
export class ProfileSettingsComponent implements OnInit {
  @Input() tokens: LanguageTokens;
  authenticated: Employee;
  editable = true;
  @ViewChild(SuccessComponent)
  private successComponent: SuccessComponent;
  @ViewChild(ResponsiveCircularLoaderComponent)
  private circularLoader: ResponsiveCircularLoaderComponent;
  form: FormGroup;

  constructor(
    private authService: AuthService,
    private employeeService: EmployeeService
  ) { }

  ngOnInit(): void {
    this.initForm();
    this.authService.authenticatedSub.subscribe(auth => {
      this.authenticated = auth;
      this.patchForm(this.authenticated);
    });
  }

  initForm(): void {
    this.form = new FormGroup({
      name: new FormControl(),
      country: new FormControl(),
      city: new FormControl(),
      postcode: new FormControl(),
      bio: new FormControl(),
    });
  }

  onSubmit() {
    const value = this.form.value;
    const employee: Employee = {
      ...this.authenticated,
      name: value.name,
      description: value.bio,
      address: {
        country: value.country,
        city: value.city,
        postcode: value.postcode
      }
    };
    this.updateProfile(employee);
  }

  updateProfile(employee: Employee): void {
    this.startLoading();
    this.employeeService.update(employee)
      .subscribe(
        updated => {
          this.authService.update(updated);
          this.patchForm(updated);
          this.success();
        },
        error => {
          MaterialService.toast(error);
          this.endLoading();
        }
      );
  }

  patchForm(employee: Employee): void {
    this.form.patchValue({
      name: employee.name,
      bio: employee.description,
      country: employee.address.country,
      city: employee.address.city,
      postcode: employee.address.postcode
    }, {emitEvent: true});
    MaterialService.updateInputs();
  }

  private startLoading(): void {
    this.editable = false;
    this.circularLoader.startLoading();
  }

  private endLoading(): void {
    this.circularLoader.endLoading();
    this.editable = true;
  }

  private success(): void {
    this.circularLoader.endLoading();
    this.successComponent.success(() => this.editable = true);
  }
}
