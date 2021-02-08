import {Component, EventEmitter, Output, ViewChild} from '@angular/core';
import {ImageHandlerComponent} from '../../../shared/component/image-handler/image-handler.component';

@Component({
  selector: 'app-product-image',
  template: `
    <app-image-handler
      (removeImageEvent)="onRemove()"
      (uploadImageEvent)="onUpload($event)"
    ></app-image-handler>
  `
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
