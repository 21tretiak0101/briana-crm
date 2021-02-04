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
import {
  MaterialInstance,
  MaterialService
} from '../../service/material/material.service';
import {
  DynamicFormGroup,
  DynamicFormGroupValues,
  DynamicQuestionGroup
} from '../dynamic-question/dynamic-question.component';
import {DynamicFormService} from '../../service/dynamic-question/dynamic-form.service';
import {SuccessComponent} from '../success/success.component';
import {CircularLoaderComponent} from '../circular-loader/circular-loader.component';

@Component({
  selector: 'app-modal-handler',
  templateUrl: './modal-handler.component.html',
  styleUrls: ['./modal-handler.component.css']
})
export class ModalHandlerComponent implements AfterViewInit, OnDestroy, OnInit {
  @Input() questionGroups: DynamicQuestionGroup[];
  @ViewChild('modal') modalRef: ElementRef;
  @Output() submitEvent = new EventEmitter<DynamicFormGroupValues>();

  @ViewChild(SuccessComponent)
  private successComponent: SuccessComponent;

  @ViewChild(CircularLoaderComponent)
  private circularLoader: CircularLoaderComponent;

  materialModal: MaterialInstance;
  dynamicFormGroups: DynamicFormGroup[];
  editable = true;

  constructor(private dynamicFormService: DynamicFormService) { }

  ngOnInit(): void {
    this.dynamicFormGroups = this.questionGroups.map(
      this.dynamicFormService.toDynamicFormGroup
    );
  }

  ngAfterViewInit(): void {
    this.materialModal = MaterialService.initModal(this.modalRef);
  }

  ngOnDestroy() {
    this.materialModal.destroy();
  }

  open(values: DynamicFormGroupValues) {
    this.patchValues(values);
    this.materialModal.open();
  }

  onSubmit(): void {
    this.circularLoader.startLoading();
    this.submitEvent.emit(
      this.dynamicFormService.getValues(this.dynamicFormGroups)
    );
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

  private patchValues(values: DynamicFormGroupValues): void {
    this.dynamicFormService.patchValues(values, this.dynamicFormGroups);
    MaterialService.updateInputs();
  }
}
