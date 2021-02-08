import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {MaterialService} from '../material/material.service';

@Injectable()
export abstract class ImageService {
  abstract remove(): Observable<any>;

  abstract upload(image: File): Observable<any>;

  error(error: any) {
    MaterialService.error(error);
  }
}
