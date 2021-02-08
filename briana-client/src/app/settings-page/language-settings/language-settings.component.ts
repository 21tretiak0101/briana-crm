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
  AVAILABLE_LANGUAGES,
  LanguageTokens
} from '../../shared/service/language/languages';
import {LanguageService} from '../../shared/service/language/language.service';
import {FormControl, FormGroup} from '@angular/forms';
import {
  MaterialInstance,
  MaterialService
} from '../../shared/service/material/material.service';

@Component({
  selector: 'app-language-settings',
  templateUrl: './language-settings.component.html'
})
export class LanguageSettingsComponent implements
  OnInit,
  OnDestroy,
  AfterViewInit {

  @ViewChild('language') languageRef: ElementRef;
  @Input() tokens: LanguageTokens;
  availableLanguages = AVAILABLE_LANGUAGES;
  activeLanguageId: string;
  select: MaterialInstance;
  form: FormGroup;

  constructor(private languageService: LanguageService) {
    this.activeLanguageId = this.languageService.activeLanguageId;
  }

  ngOnInit(): void {
    this.initForm();
  }

  ngAfterViewInit(): void {
    this.select = MaterialService.initSelect(this.languageRef);
  }

  ngOnDestroy(): void {
    this.select.destroy();
  }

  initForm(): void {
    this.form = new FormGroup({
      language: new FormControl(this.activeLanguageId)
    });
  }

  onSubmit() {
    const {language} = this.form.value;
    if (language && language !== this.activeLanguageId) {
      this.activeLanguageId = language;
      this.languageService.changeLanguage(this.form.value.language);
    }
  }
}
