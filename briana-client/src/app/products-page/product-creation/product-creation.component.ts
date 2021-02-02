import {Component} from '@angular/core';
import {Product} from '../../shared/entities';
import {ProductService} from '../../shared/service/product/product.service';
import {MaterialService} from '../../shared/service/material/material.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-product-creation',
  templateUrl: './product-creation.component.html',
  styleUrls: ['./product-creation.component.css']
})
export class ProductCreationComponent {
  image: File;

  constructor(private productService: ProductService,
              private router: Router) { }

  onFileRemove() {
    this.image = null;
  }

  onFileUpload(file: File) {
    this.image = file;
  }

  onProductCreate(product: Product) {
    this.productService.create(product, this.image).subscribe(
      () => {
        this.router.navigate(['/products']);
      },
      MaterialService.error
    );
  }
}
