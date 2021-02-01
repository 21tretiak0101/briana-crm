import {Component, Input, ViewChild} from '@angular/core';
import {ModalHandlerComponent} from '../../../shared/component/modal-handler/modal-handler.component';
import {DynamicQuestion} from '../../../shared/component/dynamic-question/dynamic-question.component';
import {Category} from '../../../shared/entities';
import {CategoryService} from '../../../shared/service/category/category.service';
import {MaterialService} from '../../../shared/service/material/material.service';

@Component({
  selector: 'app-category-edit-modal',
  template:  `
    <app-modal-handler
      [questions]="questions"
      (submitEvent)="onSubmit($event)"
    ></app-modal-handler>
  `
})
export class CategoryEditModalComponent {
  @Input() category: Category;
  questions: DynamicQuestion[] = [
    new DynamicQuestion({
      key: 'name',
      label: 'name'
    }),
    new DynamicQuestion({
      key: 'description',
      label: 'desc'
    })
  ];
  @ViewChild(ModalHandlerComponent)
  private modalHandler: ModalHandlerComponent;

  constructor(private categoryService: CategoryService) { }

  open(): void {
    this.modalHandler.open(this.category);
  }

  onSubmit({name, description}: any) {
    this.categoryService.update({...this.category, name, description})
      .subscribe(
        () => {
          this.modalHandler.success();
        },
        error => {
          MaterialService.error(error.message);
          this.modalHandler.error();
        }
      );
  }
}
