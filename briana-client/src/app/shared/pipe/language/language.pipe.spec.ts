import {LanguagePipe} from './language.pipe';
import {LanguageService} from '../../service/language/language.service';

describe('LanguagePipe', () => {
  it('create an instance', () => {
    const pipe = new LanguagePipe(new LanguageService());
    expect(pipe).toBeTruthy();
  });
});
