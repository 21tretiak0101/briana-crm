import {Component, OnInit} from '@angular/core';
import {TranslationToken} from '../shared/service/language/languages';
import {LanguageService} from '../shared/service/language/language.service';
import {Observable} from 'rxjs';
import {ClientService} from '../shared/service/client/client.service';
import {Client} from '../shared/entities';
import {ClientFilter} from './client-filter/client-filter.component';
import {ClientModal} from './client-modal/client-modal.component';
import {delay} from 'rxjs/operators';

const STEP = 5;

@Component({
  selector: 'app-clients-page',
  templateUrl: './clients-page.component.html',
  styleUrls: ['./clients-page.component.css']
})
export class ClientsPageComponent implements OnInit {
  l: TranslationToken;
  clients: Client[];
  isFilterVisible = false;
  loading = false;
  pageNumber = 0;
  limit = STEP;
  modal: ClientModal;
  isLoading = false;
  params = {};

  constructor(private lang: LanguageService,
              private clientService: ClientService) { }

  ngOnInit(): void {
    this.l = this.lang.getCurrent();
    this.fetchAll();
  }

  applyFilter(filter: ClientFilter) {
    this.fetchAll(filter);
  }

  loadMore(): void {
    this.pageNumber += STEP;
    this.fetchAll();
  }

  initModal(modal: ClientModal) {
    this.modal = modal;
  }

  createClient() {
    this.modal.create();
  }

  private fetchAll(params = this.params): void {
    this.isLoading = true;
    this.params = params;
    this.clientService.getAll({
        pageNumber: this.pageNumber,
        limit: this.limit,
        ...params
      }
    ).pipe(delay(1000)).subscribe(
      filtered => {
        this.clients = filtered;
        this.isLoading = false;
      }
    );
  }
}
