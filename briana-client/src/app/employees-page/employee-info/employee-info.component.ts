import {Component, OnInit} from '@angular/core';
import {EmployeeService} from '../../shared/service/employee/employee.service';
import {ActivatedRoute} from '@angular/router';
import {switchMap} from 'rxjs/operators';
import {Employee} from '../../shared/entities';
import {DEFAULT_AVATAR_PATH} from '../../../environments/environment';

@Component({
  selector: 'app-employee-info',
  templateUrl: './employee-info.component.html',
  styleUrls: ['./employee-info.component.css']
})
export class EmployeeInfoComponent implements OnInit {
  employee: Employee;
  defaultAvatar = DEFAULT_AVATAR_PATH;

  constructor(
    private employeeService: EmployeeService,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.activatedRoute.params.pipe(
      switchMap(params => {
        return this.employeeService.getById(params.id);
      })
    ).subscribe((employee) => {
      this.employee = employee;
    });
  }
}
