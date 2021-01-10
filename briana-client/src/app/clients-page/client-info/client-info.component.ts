import {
  AfterViewInit,
  Component,
  ElementRef,
  OnDestroy,
  OnInit,
  ViewChild
} from '@angular/core';
import {
  MaterialService,
  Tabs
} from '../../shared/service/material/material.service';
import {ActivatedRoute, Params} from '@angular/router';
import {ClientService} from '../../shared/service/client/client.service';
import {Client} from '../../shared/entities';
import {LanguageService} from '../../shared/service/language/language.service';
import {TranslationToken} from '../../shared/service/language/languages';
import {switchMap} from 'rxjs/operators';
import {Observable, throwError} from 'rxjs';
import {ClientModal} from '../client-modal/client-modal.component';

@Component({
  selector: 'app-client-info',
  templateUrl: './client-info.component.html',
  styleUrls: ['./client-info.component.css']
})
export class ClientInfoComponent implements OnInit, AfterViewInit, OnDestroy {
  client: Client;
  @ViewChild('tabs') tabsRef: ElementRef;
  @ViewChild('overview') overviewRef: ElementRef;
  l: TranslationToken;
  tabs: Tabs;
  modal: ClientModal;

  constructor(private route: ActivatedRoute,
              private clientService: ClientService,
              private lang: LanguageService) { }

  ngOnInit(): void {
    this.l = this.lang.getCurrent();
    this.setClient();
  }

  ngOnDestroy(): void {
   this.tabs.destroy();
  }

  ngAfterViewInit(): void {
    this.tabs = MaterialService.initTabs(this.tabsRef);
  }

  setClient(): void {
    this.route.params
      .pipe(
        switchMap(
          (params: Params): Observable<Client | never> => {
            const id: number = +params['id'];
            return id
              ? this.clientService.getById(id)
              : throwError(new Error('user not found'));
        })
      ).subscribe(
        (client: Client) => {
          this.client = client;
        },
      error => {
          MaterialService.toast(error.message);
      }
    );
  }

  clientImage(): string {
    return this.client.name.slice(0, 2).toUpperCase();
  }

  update() {
    this.modal.update(this.client);
  }

  initModal(modal: ClientModal) {
    this.modal = modal;
  }

  setUpdated(updated: Client) {
    this.client = {...this.client, ...updated};
  }
}
