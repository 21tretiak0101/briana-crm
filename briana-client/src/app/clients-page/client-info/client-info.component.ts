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
import {switchMap} from 'rxjs/operators';
import {Observable, throwError} from 'rxjs';
import {ClientModalComponent} from '../client-modal/client-modal.component';

@Component({
  selector: 'app-client-info',
  templateUrl: './client-info.component.html',
  styleUrls: ['./client-info.component.css']
})
export class ClientInfoComponent implements OnInit, AfterViewInit, OnDestroy {
  @ViewChild('tabs') tabsRef: ElementRef;
  @ViewChild('overview') overviewRef: ElementRef;
  @ViewChild(ClientModalComponent) clientModal: ClientModalComponent;
  client: Client;
  tabs: Tabs;

  constructor(
    private route: ActivatedRoute,
    private clientService: ClientService
  ) { }

  ngOnInit(): void {
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

  onUpdate(updated: Client) {
    this.client = {...this.client, ...updated};
  }
}
