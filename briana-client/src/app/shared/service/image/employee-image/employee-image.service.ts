import {Injectable} from '@angular/core';
import {ImageService} from '../image.service';
import {Observable} from 'rxjs';
import {EmployeeService} from '../../employee/employee.service';

@Injectable()
export class EmployeeImageService extends ImageService {
  constructor(private employeeService: EmployeeService) {
    super();
  }

  remove(): Observable<any> {
    return this.employeeService.removeImage();
  }

  upload(image: File): Observable<any> {
    return this.employeeService.updateImage(image);
  }
}
