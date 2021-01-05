import {Component, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {TranslationToken} from '../../shared/service/language/languages';
import {AuthService} from '../../shared/service/auth/auth.service';
import {Employee} from '../../shared/entities';
import {EmployeeService} from '../../shared/service/employee/employee.service';
import {MaterialService} from '../../shared/service/material/material.service';

@Component({
  selector: 'app-profile-settings',
  templateUrl: './profile-settings.component.html',
  styleUrls: ['./profile-settings.component.css']
})
export class ProfileSettingsComponent implements OnInit {
  @Input() l: TranslationToken;
  form: FormGroup;
  authenticated: Employee;
  loading = false;
  success = false;

  constructor(private auth: AuthService,
              private employeeService: EmployeeService) { }

  ngOnInit(): void {
    this.authenticated = this.auth.getAuthenticated();
    this.initForm();
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
    this.setSuccess();
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
  //  this.updateProfile(employee);
  }

  updateProfile(employee: Employee): void {
    this.loading = true;
    this.employeeService.update(employee)
      .subscribe(
        updated => {
        this.patchForm(updated);
        this.loading = false;
        this.setSuccess();
      },
        error => {
          MaterialService.toast(error);
          this.loading = false;
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
    });
    MaterialService.updateInputs();
  }

  setSuccess(): void {
    this.loading = true;
    setTimeout(() => {
      this.loading = false;
      this.success = true;
      setTimeout(() => {
        this.success = false;
      }, 2000);
    }, 1000);
  }
}
