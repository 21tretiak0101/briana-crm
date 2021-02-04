import {Component, EventEmitter, Input, Output, ViewChild} from '@angular/core';
import {ModalHandlerComponent} from '../../shared/component/modal-handler/modal-handler.component';
import {Product} from '../../shared/entities';
import {ProductService} from '../../shared/service/product/product.service';
import {
  DynamicFormGroupValues,
  DynamicQuestion,
  DynamicQuestionGroup
} from '../../shared/component/dynamic-question/dynamic-question.component';

@Component({
  selector: 'app-product-modal',
  template: `
    <app-modal-handler
      (submitEvent)="onSubmit($event)"
      [questionGroups]="dynamicQuestionGroup"
    ></app-modal-handler>
  `
})
export class ProductModalComponent {
  @Input() product: Product;
  @Output() updateProductEvent = new EventEmitter<Product>();
  @ViewChild(ModalHandlerComponent)
  private modalHandler: ModalHandlerComponent;
  private groupName = 'product';
  dynamicQuestionGroup: DynamicQuestionGroup[] = [{
    groupName: this.groupName,
    questions: [
      new DynamicQuestion({
        key: 'price',
        label: 'price',
        type: 'text'
      }),
      new DynamicQuestion({
        key: 'description',
        label: 'desc',
        type: 'text'
      }),
    ]
  }];

  constructor(private productService: ProductService) { }

  open(): void {
    this.modalHandler.open({
      [this.groupName]: this.product
    });
  }

  onSubmit(formGroupValue: DynamicFormGroupValues) {
    const {price, description} = formGroupValue[this.groupName];
    const updated = {...this.product, price, description};
    this.productService.update(updated).subscribe(
      pr => {
        this.updateProductEvent.emit(pr);
        this.modalHandler.success();
      }
    );
  }
}
