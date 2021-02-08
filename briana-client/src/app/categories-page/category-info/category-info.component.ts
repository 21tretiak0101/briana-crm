import {Component, OnInit, ViewChild} from '@angular/core';
import {Category, Product} from '../../shared/entities';
import {ActivatedRoute, Router} from '@angular/router';
import {switchMap} from 'rxjs/operators';
import {CategoryService} from '../../shared/service/category/category.service';
import {MaterialService} from '../../shared/service/material/material.service';
import {Observable} from 'rxjs';
import {ProductService} from '../../shared/service/product/product.service';
import {CategoryModalComponent} from './category-modal/category-modal.component';

@Component({
  selector: 'app-category-info',
  templateUrl: './category-info.component.html',
  styleUrls: ['./category-info.component.css']
})
export class CategoryInfoComponent implements OnInit {
  products$: Observable<Product[]>;
  category: Category;
  isLoading = false;
  isSuccess = false;
  @ViewChild(CategoryModalComponent)
  private editModal: CategoryModalComponent;

  constructor(
    private categoryService: CategoryService,
    private activatedRoute: ActivatedRoute,
    private productService: ProductService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.activatedRoute.params.pipe(
      switchMap(params => {
        return this.categoryService.getById(params.id);
      })
    ).subscribe((category) => {
      this.category = category;
      this.products$ = this.productService.getAll();
    },
      MaterialService.error
    );
  }

  onEdit() {
    this.editModal.open();
  }

  onRemove() {
    const decision = window.confirm('Do you want to remove the category?');
    if (decision) {
      this.remove();
    }
  }

  private remove(): void {
    this.categoryService.remove(this.category.id).subscribe(
      () => {
        this.router.navigate(['/categories']);
      }
    );
  }
}
