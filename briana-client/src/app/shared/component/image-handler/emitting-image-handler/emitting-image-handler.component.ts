import {Component, EventEmitter, Output, ViewChild} from '@angular/core';
import {ImageHandlerComponent} from '../image-handler.component';

@Component({
  selector: 'app-emitting-image-handler',
  template: `
    <app-image-handler
      (removeImageEvent)="onRemove()"
      (uploadImageEvent)="onUpload($event)"
    ></app-image-handler>
  `
})
export class EmittingImageHandlerComponent {
  @Output() fileUploadEvent = new EventEmitter<File>();
  @Output() fileRemoveEvent = new EventEmitter<void>();
  @ViewChild(ImageHandlerComponent)
  private imageHandler: ImageHandlerComponent;

  onUpload(file: File) {
    this.imageHandler.set(file);
    this.fileUploadEvent.emit(file);
  }

  onRemove() {
    this.imageHandler.remove();
    this.fileRemoveEvent.emit();
  }
}
