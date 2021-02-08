import {Component, Input} from '@angular/core';
import {FormGroup} from '@angular/forms';

export interface DynamicQuestionGroup {
  groupName: string;
  questions: DynamicQuestion[];
}

export interface DynamicFormGroupValues {
  [groupName: string]: any;
}

export interface DynamicFormGroup extends DynamicQuestionGroup {
  formGroup: FormGroup;
}

export class DynamicQuestion {
  key: string;
  label: string;
  controlType: string;
  type: string;
  icon: string;

  constructor(options: {
    key?: string;
    label?: string;
    controlType?: string;
    type?: string;
    icon?: string;
  } = {}) {
    this.key = options.key || '';
    this.label = options.label || '';
    this.controlType = options.controlType || '';
    this.type = options.type || '';
    this.icon = options.icon || '';
  }
}

@Component({
  selector: 'app-dynamic-question',
  templateUrl: './dynamic-question.component.html'
})

export class DynamicQuestionComponent {
  @Input() formGroup: FormGroup;
  @Input() question: DynamicQuestion;
}
