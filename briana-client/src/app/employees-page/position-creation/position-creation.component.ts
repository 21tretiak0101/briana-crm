import {
  AfterViewInit,
  Component,
  ElementRef,
  OnDestroy,
  OnInit,
  ViewChild
} from '@angular/core';
import {CreationHandlerComponent} from '../../shared/component/creation-handler/creation-handler.component';
import {FormControl, FormGroup} from '@angular/forms';
import {Position} from '../../shared/entities';
import {Observable} from 'rxjs';
import {PositionService} from '../../shared/service/position/position.service';
import {CreationService} from '../../shared/service/creation/creation.service';
import {PositionCreationService} from '../../shared/service/creation/position-creation/position-creation.service';
import {
  MaterialInstance,
  MaterialService
} from '../../shared/service/material/material.service';

@Component({
  selector: 'app-position-creation',
  templateUrl: './position-creation.component.html',
  viewProviders: [{
    provide: CreationService,
    useClass: PositionCreationService
  }]
})
export class PositionCreationComponent implements
  OnInit,
  AfterViewInit,
  OnDestroy {
  @ViewChild('permissions') permissionsRef: ElementRef;
  availablePermissions$: Observable<string[]>;
  form: FormGroup;
  @ViewChild(CreationHandlerComponent)
  private creationHandler: CreationHandlerComponent;
  private select: MaterialInstance;

  constructor(private positionService: PositionService) { }

  ngOnInit(): void {
    this.availablePermissions$ = this.positionService.getAllPermissions();
    this.form = new FormGroup({
      name: new FormControl(),
      description: new FormControl(),
      permissions: new FormControl(),
    });
  }

  ngAfterViewInit(): void {
    this.select = MaterialService.initSelect(this.permissionsRef);
  }

  ngOnDestroy(): void {
    this.select.destroy();
  }

  onSubmit() {
    const {name, description, permissions} = this.form.value;
    const position: Position = {name, description, permissions};
    this.creationHandler.save(position);
    console.log(position);
  }
}
