import {Component, Input} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {ProductQuestion} from '../../../products-page/product-modal/product-modal.component';

@Component({
  selector: 'app-dynamic-question',
  templateUrl: './dynamic-question.component.html',
  styleUrls: ['./dynamic-question.component.css']
})

export class DynamicQuestionComponent {
  @Input() form: FormGroup;
  @Input() question: ProductQuestion;
}
