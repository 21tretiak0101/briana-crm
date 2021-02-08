import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {DEFAULT_LANGUAGE, LanguageTokens, TOKENS} from './languages';

@Injectable({
  providedIn: 'root',
})
export class LanguageService {
  activeTokens: LanguageTokens = this.generateTokens(DEFAULT_LANGUAGE.id);
  activeLanguageId = DEFAULT_LANGUAGE.id;

  private behaviorSubject = new BehaviorSubject<LanguageTokens>(
    this.activeTokens
  );

  get tokens(): Observable<LanguageTokens> {
    return this.behaviorSubject.asObservable();
  }

  changeLanguage(languageId: string) {
    this.activeLanguageId = languageId;
    this.activeTokens = this.generateTokens(languageId);
    this.behaviorSubject.next(this.activeTokens);
  }

  get(token: string): string {
    return this.activeTokens[token] as string;
  }

  private generateTokens(languageId: string): LanguageTokens {
    return Object.keys(TOKENS).reduce((generated, token) => {
      generated[token] = TOKENS[token][languageId];
      return generated;
    }, {});
  }
}
