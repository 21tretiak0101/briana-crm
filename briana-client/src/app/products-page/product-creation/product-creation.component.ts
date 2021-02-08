import {
  AfterViewInit,
  Component,
  ElementRef,
  OnDestroy,
  OnInit,
  ViewChild
} from '@angular/core';
import {Category, Product} from '../../shared/entities';
import {
  MaterialInstance,
  MaterialService
} from '../../shared/service/material/material.service';
import {FormControl, FormGroup} from '@angular/forms';
import {Observable} from 'rxjs';
import {CreationHandlerComponent} from '../../shared/component/creation-handler/creation-handler.component';
import {CreationService} from '../../shared/service/creation/creation.service';
import {ProductCreationService} from '../../shared/service/creation/product-creation/product-creation.service';
import {CategoryService} from '../../shared/service/category/category.service';

@Component({
  selector: 'app-product-creation',
  templateUrl: './product-creation.component.html',
  viewProviders: [{
    provide: CreationService,
    useClass: ProductCreationService
  }]
})
export class ProductCreationComponent implements
  OnInit,
  AfterViewInit,
  OnDestroy {
  @ViewChild('category') languageRef: ElementRef;
  availableCategories$: Observable<Category[]>;
  categorySelect: MaterialInstance;
  form: FormGroup;
  @ViewChild(CreationHandlerComponent)
  private creationHandler: CreationHandlerComponent;
  image: File;

  constructor(private categoryService: CategoryService) { }

  ngOnInit() {
    this.availableCategories$ = this.categoryService.getAll();
    this.form = new FormGroup({
      name: new FormControl(),
      description: new FormControl(),
      price: new FormControl(),
      category: new FormControl(),
    });
  }

  ngAfterViewInit() {
    this.categorySelect = MaterialService.initSelect(this.languageRef);
  }

  ngOnDestroy() {
    this.categorySelect.destroy();
  }

  onFileRemove() {
    this.image = null;
  }

  onFileUpload(file: File) {
    this.image = file;
  }

  onSubmit() {
    const {category: categoryId, description, price, name} = this.form.value;
    const product: Product = {
      name,
      price,
      description,
      categoryId
    };
    this.creationHandler.save(product, this.image);
  }
}
