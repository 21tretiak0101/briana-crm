import {Pipe, PipeTransform} from '@angular/core';
import {LanguageService} from '../../service/language/language.service';

@Pipe({
  name: 'lang'
})
export class LanguagePipe implements PipeTransform {
  constructor(private lang: LanguageService) { }

  transform(key: string): string {
    return this.lang.get(key);
  }
}
