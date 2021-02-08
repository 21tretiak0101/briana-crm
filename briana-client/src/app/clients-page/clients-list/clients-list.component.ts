import {Component, Input} from '@angular/core';
import {Client} from '../../shared/entities';

@Component({
  selector: 'app-clients-list',
  templateUrl: './clients-list.component.html',
  styleUrls: ['./clients-list.component.css']
})
export class ClientsListComponent {
  @Input() clients: Client[];
}
