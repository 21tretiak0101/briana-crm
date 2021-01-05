import {
  AfterViewInit,
  Component,
  ElementRef,
  Input,
  OnDestroy,
  OnInit,
  ViewChild
} from '@angular/core';
import {
  AVAILABLE_LANGUAGES, DEFAULT_LANGUAGE,
  TranslationToken
} from '../../shared/service/language/languages';
import {LanguageService} from '../../shared/service/language/language.service';
import {FormControl, FormGroup} from '@angular/forms';
import {
  MaterialInstance,
  MaterialService
} from '../../shared/service/material/material.service';

@Component({
  selector: 'app-language-settings',
  templateUrl: './language-settings.component.html',
  styleUrls: ['./language-settings.component.css']
})
export class LanguageSettingsComponent implements
  OnInit,
  OnDestroy,
  AfterViewInit {

  l: TranslationToken;
  activeLanguage: string = DEFAULT_LANGUAGE;
  @ViewChild('language') languageRef: ElementRef;
  availableLanguages = AVAILABLE_LANGUAGES;
  form: FormGroup;
  select: MaterialInstance;

  constructor(private lang: LanguageService) { }

  ngOnInit(): void {
    this.initForm();
    this.activeLanguage = 'en';
    this.l = this.lang.getCurrent();
    console.log(this.availableLanguages);
  }

  ngAfterViewInit(): void {
    this.select = MaterialService.initSelect(this.languageRef);
  }

  ngOnDestroy(): void {
    this.select.destroy();
  }

  initForm(): void {
    this.form = new FormGroup({
      language: new FormControl()
    });
  }

  onSubmit() {
    this.lang.switchTo(this.form.value.language);
  }

  func(x) {
    const equal = this.activeLanguage === x.id;
    console.log(x, equal);
    return equal;
  }
}
