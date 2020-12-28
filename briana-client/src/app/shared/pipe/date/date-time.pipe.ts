import {Pipe, PipeTransform} from '@angular/core';
import {LanguageService} from '../../service/language/language.service';
import {DatePipe} from '@angular/common';

@Pipe({
  name: 'dateTime',
})
export class DateTimePipe implements PipeTransform {
  constructor(private lang: LanguageService,
              private date: DatePipe) { }

  transform(value: Date): string {
    const date = this.date.transform(value, 'dd.MM');
    const time = this.date.transform(value, 'hh:mm');
    const prefix = this.lang.getCurrent()['at'];
    return `${date} ${prefix} ${time}`;
  }
}
