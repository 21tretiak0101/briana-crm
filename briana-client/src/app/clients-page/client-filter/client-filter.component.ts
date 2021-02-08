import {Component, EventEmitter, Output} from '@angular/core';
import {Client} from '../../shared/entities';

export type ClientFilter = Pick<Client, 'id' | 'name' | 'email'>;

@Component({
  selector: 'app-client-filter',
  templateUrl: './client-filter.component.html',
  styleUrls: ['./client-filter.component.css']
})
export class ClientFilterComponent {
  @Output() filterEventEmitter = new EventEmitter<ClientFilter>();
  id: number;
  name: string;
  email: string;

  submit() {
    this.filterEventEmitter.emit({
      id: this.id,
      name: this.name,
      email: this.email
    });
  }
}
