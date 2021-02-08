import {
  Component,
  EventEmitter,
  Host,
  Input,
  OnDestroy,
  Output,
  ViewChild
} from '@angular/core';
import {ImageService} from '../../../service/image/image.service';
import {ImageHandlerComponent} from '../image-handler.component';
import {DEFAULT_PRODUCT_IMAGE_PATH} from '../../../../../environments/environment';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-generic-image-handler',
  template: `
    <app-image-handler
      [source]="source"
      [defaultSource]="defaultSource"
      (uploadImageEvent)="onUpload($event)"
      (removeImageEvent)="onRemove()"
    ></app-image-handler>
  `
})
export class GenericImageHandlerComponent implements OnDestroy {
  @Input() source: string;
  @Input() defaultSource: string = DEFAULT_PRODUCT_IMAGE_PATH;
  @Output() successUploadEvent = new EventEmitter<File>();
  @Output() successRemoveEvent = new EventEmitter<void>();
  uploadSubscriber: Subscription;
  removeSubscriber: Subscription;
  @ViewChild(ImageHandlerComponent)
  private imageHandler: ImageHandlerComponent;

  constructor(@Host() private imageService: ImageService) { }

  ngOnDestroy(): void {
    [this.uploadSubscriber, this.removeSubscriber]
      .filter(Boolean)
      .forEach(sub => sub.unsubscribe());
  }

  onUpload(file: File) {
    this.imageHandler.switchLoading();
    this.uploadSubscriber = this.imageService.upload(file)
      .subscribe(
        () => {
          this.imageHandler.set(file);
          this.imageHandler.switchLoading();
          this.successUploadEvent.emit(file);
        },
        error => {
          this.imageHandler.switchLoading();
          this.imageService.error(error);
        }
      );
  }

  onRemove() {
    this.imageHandler.switchLoading();
    this.removeSubscriber = this.imageService.remove()
      .subscribe(
        () => {
          this.imageHandler.remove();
          this.imageHandler.switchLoading();
          this.successRemoveEvent.emit();
        },
        error => {
          this.imageHandler.switchLoading();
          this.imageService.error(error);
        }
      );
  }
}
