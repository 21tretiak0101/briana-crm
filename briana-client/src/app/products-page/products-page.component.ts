import {Component, OnInit} from '@angular/core';
import {Product} from '../shared/entities';
import {Observable} from 'rxjs';
import {ProductService} from '../shared/service/product/product.service';

@Component({
  selector: 'app-category-page',
  templateUrl: './products-page.component.html',
  styleUrls: ['./products-page.component.css']
})
export class ProductsPageComponent implements OnInit {
  products$: Observable<Product[]>;

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.products$ = this.productService.getAll();
  }
}
