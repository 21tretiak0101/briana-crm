import {Component, OnInit} from '@angular/core';
import {CategoryService} from '../shared/service/category/category.service';
import {Category} from '../shared/entities';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-categories-page',
  templateUrl: './categories-page.component.html'
})
export class CategoriesPageComponent implements OnInit {
  categories$: Observable<Category[]>;

  constructor(private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.categories$ = this.categoryService.getAll();
  }
}
