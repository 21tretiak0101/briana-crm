import {
  Component,
  ElementRef,
  EventEmitter,
  Input,
  Output,
  ViewChild
} from '@angular/core';
import {DEFAULT_PRODUCT_IMAGE_PATH} from '../../../../environments/environment';

export interface ImageForm {
  file?: File;
  set(file: File): void;
  remove(): void;
  switchLoading(): void;
}

@Component({
  selector: 'app-image-handler',
  templateUrl: './image-handler.component.html',
  styleUrls: ['./image-handler.component.css']
})
export class ImageHandlerComponent {
  @ViewChild('inputElement') inputElement: ElementRef;
  @Input() source: string | ArrayBuffer;
  @Input() defaultSource: string;
  @Output() removeImageEvent: EventEmitter<ImageForm> = new EventEmitter();
  @Output() uploadImageEvent: EventEmitter<ImageForm> = new EventEmitter();
  defaultImage: string = DEFAULT_PRODUCT_IMAGE_PATH;
  loading = false;
  imageForm: ImageForm = {
    set: this.setNew.bind(this),
    remove: this.remove.bind(this),
    switchLoading: this.switchLoading.bind(this)
  };

  trigger() {
    this.inputElement.nativeElement.click();
  }

  onUpload(event: Event): void {
    const [file] = event.target['files'];
    this.uploadImageEvent.emit({...this.imageForm, file});
    this.resetForm();
  }

  onRemove() {
    const decision = window.confirm('Do you want to remove the image?');
    if (decision) {
      this.removeImageEvent.emit(this.imageForm);
    }
  }

  switchLoading(): void {
    this.loading = !this.loading;
  }

  private resetForm(): void {
    this.inputElement.nativeElement.value = null;
  }

  private setNew(file: File): void {
    const reader = new FileReader();
    reader.onload = () => {
      this.source = reader.result;
    };
    reader.readAsDataURL(file);
  }

  private remove(): void {
    this.source = this.defaultSource || this.defaultImage;
  }
}
