import {Component, OnInit} from '@angular/core';
import {EmployeeService} from '../../shared/service/employee/employee.service';
import {ActivatedRoute} from '@angular/router';
import {switchMap} from 'rxjs/operators';
import {Employee} from '../../shared/entities';

@Component({
  selector: 'app-employee-info',
  templateUrl: './employee-info.component.html',
  styleUrls: ['./employee-info.component.css']
})
export class EmployeeInfoComponent implements OnInit {
  employee: Employee;

  constructor(private employeeService: EmployeeService,
              private activatedRoute: ActivatedRoute) { }

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
