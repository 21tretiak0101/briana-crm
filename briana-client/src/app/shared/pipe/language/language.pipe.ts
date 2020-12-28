import {Pipe, PipeTransform} from '@angular/core';
import {LanguageService} from '../../service/language/language.service';
import {TranslationToken} from '../../service/language/languages';

@Pipe({
  name: 'lang'
})
export class LanguagePipe implements PipeTransform {
  l: TranslationToken;

  constructor(private lang: LanguageService) {
    this.l = this.lang.getCurrent();
  }

  transform(key: string): string {
    return this.l[key] as string;
  }
}
