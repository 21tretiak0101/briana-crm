import {Component, ViewChild} from '@angular/core';
import {ProductService} from '../../../shared/service/product/product.service';
import {MaterialService} from '../../../shared/service/material/material.service';
import {ImageHandlerComponent} from '../../../shared/component/image-handler/image-handler.component';

@Component({
  selector: 'app-product-image',
  templateUrl: './product-image.component.html',
  styleUrls: ['./product-image.component.css']
})
export class ProductImageComponent {
  @ViewChild(ImageHandlerComponent)
  private imageHandler: ImageHandlerComponent;

  constructor(private productService: ProductService) { }

  onUpload(file: File) {
    this.imageHandler.switchLoading();
    this.productService.uploadImage(file).subscribe(
      msg => {
        console.log(msg);
        this.imageHandler.set(file);
        this.imageHandler.switchLoading();
    },
      err => {
        this.imageHandler.switchLoading();
        MaterialService.toast(err);
      });
  }

  onRemove() {
    this.imageHandler.switchLoading();
    this.productService.removeImage().subscribe(
      msg => {
        console.log(msg);
        this.imageHandler.remove();
        this.imageHandler.switchLoading();
      },
      err => {
        this.imageHandler.switchLoading();
        MaterialService.toast(err);
      }
    );
  }
}
