import {
  AfterViewInit,
  Component,
  ElementRef,
  OnDestroy,
  OnInit,
  ViewChild
} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Employee, Position} from '../../shared/entities';
import {CreationHandlerComponent} from '../../shared/component/creation-handler/creation-handler.component';
import {CreationService} from '../../shared/service/creation/creation.service';
import {EmployeeCreationService} from '../../shared/service/creation/employee-creation/employee-creation.service';
import {
  MaterialInstance,
  MaterialService
} from '../../shared/service/material/material.service';
import {PositionService} from '../../shared/service/position/position.service';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-employee-creation',
  templateUrl: './employee-creation.component.html',
  viewProviders: [{
    provide: CreationService,
    useClass: EmployeeCreationService
  }]
})
export class EmployeeCreationComponent implements
  OnInit,
  AfterViewInit,
  OnDestroy {
  @ViewChild('position') positionRef: ElementRef;
  availablePositions$: Observable<Position[]>;
  positionSelect: MaterialInstance;
  form: FormGroup;
  @ViewChild(CreationHandlerComponent)
  private creationHandler: CreationHandlerComponent;

  constructor(private positionService: PositionService) { }

  ngOnInit(): void {
    this.availablePositions$ = this.positionService.getAll();
    this.form = new FormGroup({
      name: new FormControl(),
      email: new FormControl(),
      phone: new FormControl(),
      position: new FormControl()
    });
  }

  ngAfterViewInit(): void {
    this.positionSelect = MaterialService.initSelect(this.positionRef);
  }

  ngOnDestroy(): void {
    this.positionSelect.destroy();
  }

  onSubmit() {
    const {name, email, phone, position} = this.form.value;
    const employee: Employee = {name, email, phone, positionName: position};
    this.creationHandler.save(employee);
  }
}
