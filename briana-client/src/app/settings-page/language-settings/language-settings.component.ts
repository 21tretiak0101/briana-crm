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
  TranslationToken
} from '../../shared/service/languages';
import {LanguageService} from '../../shared/service/language.service';
import {FormControl, FormGroup} from '@angular/forms';
import {
  MaterialInstance,
  MaterialService
} from '../../shared/service/material.service';

@Component({
  selector: 'app-language-settings',
  templateUrl: './language-settings.component.html',
  styleUrls: ['./language-settings.component.css']
})
export class LanguageSettingsComponent implements
  OnInit,
  OnDestroy,
  AfterViewInit {

  @Input() l: TranslationToken;
  @Input() activeLanguage: string;
  @ViewChild('language') languageRef: ElementRef;
  availableLanguages = AVAILABLE_LANGUAGES;
  form: FormGroup;
  select: MaterialInstance;

  constructor(private lang: LanguageService) { }

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
      language: new FormControl()
    });
  }

  onSubmit() {
    this.lang.switchTo(this.form.value.language);
  }
}
