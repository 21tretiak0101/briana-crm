import {Injectable} from '@angular/core';
import {CreationService} from '../creation.service';
import {Observable} from 'rxjs';
import {Client} from '../../../entities';
import {ClientService} from '../../client/client.service';

@Injectable()
export class ClientCreationService extends CreationService {

  constructor(private clientService: ClientService) {
    super();
  }

  navigationCommands(): string[] {
    return ['/clients'];
  }

  save(entity: Client): Observable<Client> {
    return this.clientService.save(entity);
  }
}
