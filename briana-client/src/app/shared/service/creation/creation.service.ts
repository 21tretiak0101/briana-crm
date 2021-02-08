import {Observable} from 'rxjs';

export abstract class CreationService {
  abstract save(entity: any, file?: File): Observable<any>;

  abstract navigationCommands(): string[];
}
