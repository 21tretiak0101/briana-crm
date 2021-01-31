import {
  AfterViewInit,
  Component,
  ElementRef,
  EventEmitter,
  Input,
  OnDestroy,
  OnInit,
  Output,
  ViewChild
} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {
  MaterialInstance,
  MaterialService
} from '../../service/material/material.service';
import {DynamicQuestion} from '../dynamic-question/dynamic-question.component';
import {DynamicQuestionService} from '../../service/dynamic-question/dynamic-question.service';
import {SuccessComponent} from '../success/success.component';
import {CircularLoaderComponent} from '../circular-loader/circular-loader.component';

@Component({
  selector: 'app-modal-handler',
  templateUrl: './modal-handler.component.html',
  styleUrls: ['./modal-handler.component.css']
})
export class ModalHandlerComponent implements AfterViewInit, OnDestroy, OnInit {
  @Input() questions: DynamicQuestion[];
  form: FormGroup;
  @ViewChild('modal') modalRef: ElementRef;
  @Output() submitEvent = new EventEmitter<any>();
  editable = true;
  materialModal: MaterialInstance;
  @ViewChild(SuccessComponent)
  private successComponent: SuccessComponent;
  @ViewChild(CircularLoaderComponent)
  private circularLoader: CircularLoaderComponent;

  constructor(private questionService: DynamicQuestionService) { }

  ngOnInit(): void {
    this.form = this.questionService.toFormGroup(this.questions);
  }

  ngAfterViewInit(): void {
    this.materialModal = MaterialService.initModal(this.modalRef);
  }

  ngOnDestroy() {
    this.materialModal.destroy();
  }

  open(model: object) {
    this.patchValue(model);
    this.materialModal.open();
  }

  onSubmit(): void {
    this.circularLoader.startLoading();
    this.submitEvent.emit(this.form.value);
    this.editable = false;
  }

  close(): void {
    this.materialModal.close();
    this.editable = true;
  }

  success(): void {
    this.circularLoader.endLoading();
    this.successComponent.success(this.close.bind(this));
  }

  error(): void {
    this.circularLoader.endLoading();
    this.editable = true;
  }

  private patchValue(model: object): void {
    this.form.patchValue(
      this.questionService.getValue(this.questions, model)
    );
    MaterialService.updateInputs();
  }
}
