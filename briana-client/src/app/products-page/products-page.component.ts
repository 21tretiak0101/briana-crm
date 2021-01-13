import {Component, OnInit} from '@angular/core';
import {Product} from '../shared/entities';
import {Observable} from 'rxjs';
import {LanguageService} from '../shared/service/language/language.service';
import {TranslationToken} from '../shared/service/language/languages';
import {ProductService} from '../shared/service/product/product.service';

@Component({
  selector: 'app-category-page',
  templateUrl: './products-page.component.html',
  styleUrls: ['./products-page.component.css']
})
export class ProductsPageComponent implements OnInit {
  categories$: Observable<Product[]>;
  l: TranslationToken;

  constructor(private productService: ProductService,
              private lang: LanguageService) {
    this.l = this.lang.getCurrent();
  }

  ngOnInit(): void {
    this.categories$ = this.productService.getAll();
  }
}
