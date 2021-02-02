import {
  AfterViewInit,
  Component,
  ElementRef,
  EventEmitter,
  OnInit,
  Output,
  ViewChild
} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Observable} from 'rxjs';
import {Category, Product} from '../../../shared/entities';
import {CategoryService} from '../../../shared/service/category/category.service';
import {
  MaterialInstance,
  MaterialService
} from '../../../shared/service/material/material.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit, AfterViewInit {
  @ViewChild('category') languageRef: ElementRef;
  @Output() productCreateEvent = new EventEmitter<Product>();
  availableCategories$: Observable<Category[]>;
  select: MaterialInstance;
  form: FormGroup;
  loading = false;
  success = false;

  constructor(private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.availableCategories$ = this.categoryService.getAll();
    this.form = new FormGroup({
      name: new FormControl(),
      description: new FormControl(),
      price: new FormControl(),
      category: new FormControl(),
    });
  }
  ngAfterViewInit(): void {
    this.select = MaterialService.initSelect(this.languageRef);
  }

  onSubmit() {
    const {category: categoryId, description, price, name} = this.form.value;
    const product: Product = {
      name,
      price,
      description,
      categoryId
    };
    this.productCreateEvent.emit(product);
  }
}
