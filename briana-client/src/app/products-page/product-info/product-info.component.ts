import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductService} from '../../shared/service/product/product.service';
import {Product} from '../../shared/entities';
import {switchMap} from 'rxjs/operators';
import {ProductModalComponent} from '../product-modal/product-modal.component';

@Component({
  selector: 'app-product-info',
  templateUrl: './product-info.component.html',
  styleUrls: ['./product-info.component.css']
})
export class ProductInfoComponent implements OnInit {
  @ViewChild(ProductModalComponent, {static: true})
  private productModal: ProductModalComponent;
  product: Product;
  isLoading: any;
  isSuccess: any;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private productService: ProductService
  ) { }

  ngOnInit(): void {
    this.activatedRoute.params.pipe(
      switchMap(params => {
        return this.productService.getById(params.id);
      })
    ).subscribe((product) => {
      this.product = product;
    });
  }

  onEdit() {
    this.productModal.open();
  }

  onRemove() {
    const decision = window.confirm('Do you want to remove the product?');
    if (decision) {
      this.remove();
    }
  }

  onUpdate(updated: Product) {
    this.product = {...this.product, ...updated};
  }

  private remove(): void {
    this.productService.remove(this.product.id).subscribe(
      () => {
        this.router.navigate(['/products']);
      }
    );
  }
}
