import {Injectable} from '@angular/core';
import {CreationService} from '../creation.service';
import {Employee} from '../../../entities';
import {EmployeeService} from '../../employee/employee.service';
import {Observable} from 'rxjs';

@Injectable()
export class EmployeeCreationService extends CreationService {

  constructor(private employeeService: EmployeeService) {
    super();
  }

  save(entity: Employee): Observable<Employee> {
    return this.employeeService.create(entity);
  }

  navigationCommands(): string[] {
    return ['/employees'];
  }
}
