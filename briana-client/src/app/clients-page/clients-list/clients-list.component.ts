import {Component, Input, OnInit} from '@angular/core';
import {Client} from '../../shared/entities';
import {TranslationToken} from '../../shared/service/language/languages';

@Component({
  selector: 'app-clients-list',
  templateUrl: './clients-list.component.html',
  styleUrls: ['./clients-list.component.css']
})
export class ClientsListComponent implements OnInit {
  @Input() clients: Client[];
  @Input() l: TranslationToken;

  constructor() { }

  ngOnInit(): void { }
}
