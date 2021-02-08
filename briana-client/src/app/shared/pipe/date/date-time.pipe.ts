import {Pipe, PipeTransform} from '@angular/core';
import {DatePipe} from '@angular/common';
import {LanguagePipe} from '../language/language.pipe';

@Pipe({
  name: 'dateTime',
})
export class DateTimePipe implements PipeTransform {
  constructor(
    private date: DatePipe,
    private lang: LanguagePipe
  ) { }

  transform(value: Date): string {
    const date = this.date.transform(value, 'dd.MM');
    const time = this.date.transform(value, 'hh:mm');
    const prefix = this.lang.transform('at');
    return [date, prefix, time].join(' ');
  }
}
