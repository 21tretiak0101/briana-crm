import {Injectable} from '@angular/core';
import {DynamicQuestion} from '../../component/dynamic-question/dynamic-question.component';
import {FormControl, FormGroup} from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class DynamicQuestionService {
  toFormGroup(questions: DynamicQuestion[] ) {
    return new FormGroup(questions.reduce((group, question) => {
      group[question.key] = new FormControl();
      return group;
    }, {}));
  }

  getValue(questions: DynamicQuestion[], obj: any): any {
    const keys = questions.map(question => question.key);
    return keys.reduce((value, key) => {
      value[key] = obj[key];
      return value;
    }, {});
  }
}
