import {Component, OnInit} from '@angular/core';
import {EmployeeService} from '../shared/service/employee/employee.service';
import {Observable} from 'rxjs';
import {Employee} from '../shared/entities';

@Component({
  selector: 'app-employees-page',
  templateUrl: './employees-page.component.html',
  styleUrls: ['./employees-page.component.css']
})
export class EmployeesPageComponent implements OnInit {
  employees$: Observable<Employee[]>;

  constructor(private employeeService: EmployeeService) { }

  ngOnInit(): void {
    this.employees$ = this.employeeService.getAll();
  }
}
