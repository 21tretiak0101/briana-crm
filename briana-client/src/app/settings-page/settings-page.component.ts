import {
  Component,
  ElementRef,
  OnDestroy,
  OnInit,
  ViewChild
} from '@angular/core';
import {LanguageService} from '../shared/service/language/language.service';
import {LanguageTokens} from '../shared/service/language/languages';
import {Subscription} from 'rxjs';
import {AuthService} from '../shared/service/auth/auth.service';
import {Employee} from '../shared/entities';

@Component({
  selector: 'app-settings-page',
  templateUrl: './settings-page.component.html',
  styleUrls: ['./settings-page.component.css']
})
export class SettingsPageComponent implements OnInit, OnDestroy {
  @ViewChild('language') languagesRef: ElementRef;
  tokens: LanguageTokens;
  subscriber$: Subscription;
  authenticated: Employee;

  constructor(
    private lang: LanguageService,
    private authService: AuthService
  ) {
    this.authenticated = this.authService.authenticated;
  }

  ngOnInit(): void {
    this.subscriber$ = this.lang.tokens.subscribe(tokens => {
      this.tokens = tokens;
    });
  }

  ngOnDestroy(): void {
    this.subscriber$.unsubscribe();
  }
}
