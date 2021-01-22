import {
  Component,
  ElementRef,
  EventEmitter,
  Input,
  Output,
  ViewChild
} from '@angular/core';
import {DEFAULT_PRODUCT_IMAGE_PATH} from '../../../../environments/environment';

@Component({
  selector: 'app-image-handler',
  templateUrl: './image-handler.component.html',
  styleUrls: ['./image-handler.component.css']
})
export class ImageHandlerComponent {
  @ViewChild('inputElement') inputElement: ElementRef;
  @Input() source: string | ArrayBuffer;
  @Input() defaultSource: string;
  @Output() removeImageEvent: EventEmitter<void> = new EventEmitter();
  @Output() uploadImageEvent: EventEmitter<File> = new EventEmitter();
  defaultImage: string = DEFAULT_PRODUCT_IMAGE_PATH;
  loading = false;

  onUpload(event: Event): void {
    const [file] = event.target['files'];
    this.uploadImageEvent.emit(file);
    this.resetForm();
  }

  trigger() {
    this.inputElement.nativeElement.click();
  }

  onRemove() {
    const decision = window.confirm('Do you want to remove the image?');
    if (decision) {
      this.removeImageEvent.emit();
    }
  }

  switchLoading(): void {
    this.loading = !this.loading;
  }

  private resetForm(): void {
    this.inputElement.nativeElement.value = null;
  }

  set(file: File): void {
    const reader = new FileReader();
    reader.onload = () => {
      this.source = reader.result;
    };
    reader.readAsDataURL(file);
  }

  remove(): void {
    this.source = this.defaultSource || this.defaultImage;
  }
}
