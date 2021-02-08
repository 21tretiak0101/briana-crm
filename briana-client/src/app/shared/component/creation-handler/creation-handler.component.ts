import {Component, EventEmitter, Host, Output, ViewChild} from '@angular/core';
import {SuccessComponent} from '../success/success.component';
import {ResponsiveCircularLoaderComponent} from '../circular-loader/responsive-circular-loader/responsive-circular-loader.component';
import {CreationService} from '../../service/creation/creation.service';
import {MaterialService} from '../../service/material/material.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-creation-handler',
  templateUrl: './creation-handler.component.html'
})
export class CreationHandlerComponent {
  @Output() submitEvent = new EventEmitter<void>();
  @ViewChild(SuccessComponent)
  private successComponent: SuccessComponent;
  @ViewChild(ResponsiveCircularLoaderComponent)
  private circularLoader: ResponsiveCircularLoaderComponent;
  editable = true;

  constructor(
    @Host() private creationService: CreationService,
    private router: Router
  ) { }

  save(entity: any, file?: File): void {
    this.startLoading();
    this.creationService.save(entity, file).subscribe(
      () => this.success(),
      error => this.error(error)
    );
  }

  private startLoading(): void {
    this.editable = false;
    this.circularLoader.startLoading();
  }

  private endLoading(): void {
    this.circularLoader.endLoading();
    this.editable = true;
  }

  private success(): void {
    this.circularLoader.endLoading();
    this.successComponent.success(() => {
      this.editable = true;
      this.router.navigate(
        this.creationService.navigationCommands()
      );
    });
  }

  private error(error): void {
    MaterialService.error(error);
    this.endLoading();
  }
}
