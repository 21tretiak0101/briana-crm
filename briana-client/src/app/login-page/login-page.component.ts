import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../shared/service/auth/auth.service';
import {Subscription} from 'rxjs';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {MaterialService} from '../shared/service/material/material.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit, OnDestroy {
  form: FormGroup;
  subscriber: Subscription;

  constructor(private auth: AuthService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.form = new FormGroup({
      email: new FormControl(
        null,
        [Validators.required, Validators.email]
      ),
      password: new FormControl(
        null,
        [Validators.required, Validators.minLength(6)]
      )
    });
  }

  ngOnDestroy(): void {
    if (this.subscriber) {
      this.subscriber.unsubscribe();
    }
  }

  onSubmit(): void {
    this.form.disable();
    this.subscriber = this.auth.login(this.form.value).subscribe(
      () => this.router.navigate(['registration']),
      (err) => {
        MaterialService.toast('ошибка');
        console.warn('error');
        this.form.enable();
      }
    );
    this.route.queryParams.subscribe((params: Params) => {
      if (params['registered']) {
        MaterialService.toast('регистрация успешна');
      } else if (params['accessDenied']) {
        MaterialService.toast('нет доступа');
      } else if (params['expired']) {
        MaterialService.toast('токен не действует');
      }
    });
  }
}
