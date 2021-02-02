import {Component, EventEmitter, Output, ViewChild} from '@angular/core';
import {ImageHandlerComponent} from '../../../shared/component/image-handler/image-handler.component';

@Component({
  selector: 'app-product-image',
  templateUrl: './product-image.component.html',
  styleUrls: ['./product-image.component.css']
})
export class ProductImageComponent {
  @Output() fileUploadEvent = new EventEmitter<File>();
  @Output() fileRemoveEvent = new EventEmitter<void>();
  @ViewChild(ImageHandlerComponent)
  private imageHandler: ImageHandlerComponent;

  onUpload(file: File) {
    this.fileUploadEvent.emit(file);
    this.imageHandler.set(file);
  }

  onRemove() {
    this.fileRemoveEvent.emit();
    this.imageHandler.remove();
  }
}
