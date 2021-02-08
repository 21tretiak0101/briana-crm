import {Component, OnInit} from '@angular/core';
import {ClientService} from '../shared/service/client/client.service';
import {Client} from '../shared/entities';
import {ClientFilter} from './client-filter/client-filter.component';
import {delay} from 'rxjs/operators';

const STEP = 5;

@Component({
  selector: 'app-clients-page',
  templateUrl: './clients-page.component.html',
  styleUrls: ['./clients-page.component.css']
})
export class ClientsPageComponent implements OnInit {
  clients: Client[];
  isFilterVisible = false;
  isLoading = false;
  pageNumber = 0;
  limit = STEP;
  params = {};

  constructor(private clientService: ClientService) { }

  ngOnInit(): void {
    this.fetchAll();
  }

  applyFilter(filter: ClientFilter) {
    this.fetchAll(filter);
  }

  onLoadMore(): void {
    this.pageNumber += STEP;
    this.fetchAll();
  }

  private fetchAll(params?: {}): void {
    this.switchLoading();
    this.params = params || this.params;
    this.clientService.getAll({
        pageNumber: this.pageNumber,
        limit: this.limit,
        ...params
      }
    ).pipe(delay(1000)).subscribe(
      filtered => {
        this.clients = filtered;
        this.switchLoading();
      }
    );
  }

  private switchLoading(): void {
    this.isLoading = !this.isLoading;
  }
}
