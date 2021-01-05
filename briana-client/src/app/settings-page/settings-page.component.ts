import {
  Component,
  ElementRef,
  OnDestroy,
  OnInit,
  ViewChild
} from '@angular/core';
import {LanguageService} from '../shared/service/language/language.service';
import {TranslationToken} from '../shared/service/language/languages';
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
  l: TranslationToken;
  activeLanguage: string;

  subscriber$: Subscription;
  authenticated: Employee;

  constructor(private lang: LanguageService,
              private auth: AuthService) { }

  ngOnInit(): void {
    this.authenticated = this.auth.getAuthenticated();
    this.activeLanguage = this.lang.activeLanguage;
    this.subscriber$ = this.lang.get().subscribe(tokens => {
      this.l = tokens;
      this.activeLanguage = this.lang.activeLanguage;
    });
  }

  ngOnDestroy(): void {
    this.subscriber$.unsubscribe();
  }
}
