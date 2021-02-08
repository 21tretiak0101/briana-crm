import {Injectable} from '@angular/core';
import {CreationService} from '../creation.service';
import {PositionService} from '../../position/position.service';
import {Observable} from 'rxjs';
import {Position} from '../../../entities';

@Injectable()
export class PositionCreationService extends CreationService {
  constructor(private positionService: PositionService) {
    super();
  }

  navigationCommands(): string[] {
    return ['/employees'];
  }

  save(entity: Position): Observable<Position> {
    return this.positionService.create(entity);
  }
}
