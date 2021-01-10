import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-client-overview',
  templateUrl: './client-overview.component.html',
  styleUrls: ['./client-overview.component.css']
})
export class ClientOverviewComponent implements OnInit {
  @Input() clientId: number;

  constructor() { }

  ngOnInit(): void { }

}
