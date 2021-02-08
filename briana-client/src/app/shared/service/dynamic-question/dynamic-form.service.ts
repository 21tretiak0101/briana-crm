import {Injectable} from '@angular/core';
import {
  DynamicFormGroup,
  DynamicFormGroupValues,
  DynamicQuestionGroup
} from '../../component/dynamic-question/dynamic-question.component';
import {FormControl, FormGroup} from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class DynamicFormService {
  toDynamicFormGroup(questionGroup: DynamicQuestionGroup): DynamicFormGroup {
    const formGroup = new FormGroup(
      questionGroup.questions.reduce((group, question) => {
        group[question.key] = new FormControl('');
        return group;
      }, {}));
    return {
      groupName: questionGroup.groupName,
      questions: questionGroup.questions,
      formGroup
    };
  }

  getValues(dynamicFormGroups: DynamicFormGroup[]): DynamicFormGroupValues {
    return dynamicFormGroups.reduce((
      dynamicValues: DynamicFormGroupValues,
      dynamicFormGroup: DynamicFormGroup
    ) => {
      dynamicValues[
        dynamicFormGroup.groupName
      ] = dynamicFormGroup.formGroup.value;
      return dynamicValues;
    }, {});
  }

  patchValues(
    values: DynamicFormGroupValues,
    dynamicFormGroups: DynamicFormGroup[]
  ): void {
    Object.keys(values).forEach(groupName => {
      this.patchValue(groupName, values[groupName], dynamicFormGroups);
    });
  }

  private patchValue(
    groupName: string,
    value: any,
    dynamicFormGroups: DynamicFormGroup[]
  ) {
    dynamicFormGroups.forEach(dynamicFormGroup => {
      if (dynamicFormGroup.groupName === groupName) {
        dynamicFormGroup.formGroup.patchValue(value);
      }
    });
  }
}
