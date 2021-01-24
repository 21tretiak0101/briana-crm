import {
  AfterViewInit,
  Component,
  ElementRef,
  EventEmitter,
  Input,
  OnDestroy,
  Output,
  ViewChild
} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {
  MaterialInstance,
  MaterialService
} from '../../service/material/material.service';
import {ProductQuestion} from '../../../products-page/product-modal/product-modal.component';

@Component({
  selector: 'app-modal-handler',
  templateUrl: './modal-handler.component.html',
  styleUrls: ['./modal-handler.component.css']
})
export class ModalHandlerComponent implements AfterViewInit, OnDestroy {
  @ViewChild('modal') modalRef: ElementRef;
  @Output() submitEvent = new EventEmitter<any>();
  @Input() form: FormGroup;
  @Input() questions: ProductQuestion[];
  materialModal: MaterialInstance;
  isSuccess = false;
  isLoading = false;

  ngAfterViewInit(): void {
    this.materialModal = MaterialService.initModal(this.modalRef);
  }

  ngOnDestroy() {
    this.materialModal.destroy();
  }

  onSubmit(): void {
    this.submitEvent.emit(this.form.value);
  }

  close(): void {
    this.materialModal.close();
  }

  open(): void {
    this.materialModal.open();
  }

  switchLoader(): void {
    this.isLoading = !this.isLoading;
  }

  success(): void {
    this.isSuccess = true;
    setTimeout(() => {
      this.isSuccess = false;
      this.close();
    }, 2500);
  }
}
