import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {DEFAULT_LANGUAGE, LANGUAGES, TranslationToken} from './languages';

@Injectable({
  providedIn: 'root',
})
export class LanguageService {
  activeLanguage: string = DEFAULT_LANGUAGE;
  private subject = new BehaviorSubject<TranslationToken>(
    this.set(DEFAULT_LANGUAGE)
  );

  switchTo(language: string) {
    this.subject.next(this.set(language));
  }

  get(): Observable<TranslationToken> {
    return this.subject.asObservable();
  }

  getCurrent(): TranslationToken {
    return this.set(this.activeLanguage);
  }

  private set(language: string): TranslationToken {
    this.activeLanguage = language;
    return Object.keys(LANGUAGES).reduce((answer, token) => {
      answer[token] = LANGUAGES[token][language];
      return answer;
    }, {});
  }
}
