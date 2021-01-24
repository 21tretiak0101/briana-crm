import {
  Component,
  EventEmitter,
  Input,
  OnInit,
  Output,
  ViewChild
} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {ModalHandlerComponent} from '../../shared/component/modal-handler/modal-handler.component';
import {Product} from '../../shared/entities';
import {MaterialService} from '../../shared/service/material/material.service';
import {ProductService} from '../../shared/service/product/product.service';

export class ProductQuestion {
  key: string;
  label: string;
  type: string;

  constructor(options: {
    key: string,
    label: string;
    type: string,
  }) {
    this.key = options.key;
    this.label = options.label;
    this.type = options.type;
  }
}

@Component({
  selector: 'app-product-modal',
  templateUrl: './product-modal.component.html',
  styleUrls: ['./product-modal.component.css']
})
export class ProductModalComponent implements OnInit {
  @Input() product: Product;
  @Output() updateProductEvent = new EventEmitter<Product>();
  form: FormGroup;
  questions: ProductQuestion[] = [
    new ProductQuestion({
      key: 'price',
      label: 'price',
      type: 'text'
    }),
    new ProductQuestion({
      key: 'description',
      label: 'desc',
      type: 'text'
    }),
  ];
  @ViewChild(ModalHandlerComponent)
  private modalHandler: ModalHandlerComponent;

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.initForm();
  }

  initForm(): void {
    const qr = this.questions.reduce((group, question) => {
      group[question.key] = new FormControl();
      return group;
    }, {});
    this.form = new FormGroup(qr);
  }

  open(): void {
    const {price, description} = this.product;
    this.form.patchValue({price, description});
    MaterialService.updateInputs();
    this.modalHandler.open();
  }

  onUpdate({price, description}) {
    this.modalHandler.switchLoader();
    const updated = {...this.product, price, description};
    this.productService.update(updated).subscribe(
      pr => {
        this.updateProductEvent.emit(pr);
        this.modalHandler.switchLoader();
        this.modalHandler.success();
      }
    );
  }

  edit(): void {
    const {price, description} = this.product;
    this.form.patchValue({price, description});
    MaterialService.updateInputs();
    this.modalHandler.open();
  }

  create(): void {
    this.form.patchValue({});
    this.modalHandler.open();
  }
}
