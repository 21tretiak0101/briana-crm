import {
  AfterViewInit,
  Component,
  ElementRef,
  Input,
  OnDestroy,
  OnInit,
  ViewChild
} from '@angular/core';
import {PositionService} from '../../shared/service/position/position.service';
import {Product} from '../../shared/entities';
import {
  MaterialInstance,
  MaterialService
} from '../../shared/service/material/material.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-position-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit, AfterViewInit, OnDestroy {
  @Input() categoryId: string;
  @ViewChild('modal') modalRef: ElementRef;
  positions: Product[] = [];
  loading = false;
  modal: MaterialInstance;
  form: FormGroup;

  constructor(private positionService: PositionService) { }

  ngOnInit(): void {
    this.form = new FormGroup({
        name: new FormControl('', [Validators.required]),
        price: new FormControl('', [Validators.required, Validators.min(0)])
      }
    );

    this.loading = true;
    this.positionService.getByCategoryId(this.categoryId).subscribe(
      positions => {
        this.positions = positions;
        this.loading = false;
      }
    );
  }

  ngOnDestroy(): void {
    this.modal.destroy();
  }

  ngAfterViewInit(): void {
    this.modal = MaterialService.initModal(this.modalRef);
  }

  onSelect(position: Product) {
    this.form.patchValue({
      name: position.name,
      price: position.price
    });
    this.modal.open();
    MaterialService.updateInputs();
  }

  onCancel() {
    this.modal.close();
  }

  onSubmit() {
    this.form.disable();
    const newPosition: Product  = {
      name: this.form.value.name,
      price: this.form.value.category,
      categoryId: this.categoryId
    };

    this.positionService.create(newPosition).subscribe(
      (position) => {
        MaterialService.toast('создано успешно');
        this.positions.push(position);
      },
      error => MaterialService.toast(error),
      () => {
        this.modal.close();
        this.form.reset({name: '', price: 0});
        this.form.enable();
      }
    );
  }

  onDelete(event: Event, position: Product) {
    event.stopPropagation();
    const decision = window.confirm(`Удалить?`);
  }
}
