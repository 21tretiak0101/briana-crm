import {Component, Input} from '@angular/core';
import {Employee} from '../../shared/entities';
import {DEFAULT_AVATAR_PATH} from '../../../environments/environment';

@Component({
  selector: 'app-employees-list',
  templateUrl: './employees-list.component.html'
})
export class EmployeesListComponent {
  @Input() employees: Employee[];
  defaultAvatar = DEFAULT_AVATAR_PATH;
}
