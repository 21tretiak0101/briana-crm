import {
  AfterViewInit,
  Component,
  ElementRef, OnDestroy,
  OnInit,
  ViewChild
} from '@angular/core';
import {
  MaterialInstance,
  MaterialService
} from '../shared/service/material/material.service';
import {OrderService} from '../shared/service/order/order.service';
import {Subscription} from 'rxjs';
import {Filter, Order} from '../shared/entities';

const STEP = 2;

@Component({
  selector: 'app-history-page',
  templateUrl: './history-page.component.html',
  styleUrls: ['./history-page.component.css']
})
export class HistoryPageComponent implements OnInit, AfterViewInit, OnDestroy {
  @ViewChild('tooltip') tooltipRef: ElementRef;
  isFilterVisible = false;
  tooltip: MaterialInstance;
  offset = 0;
  limit = STEP;
  subscriber$: Subscription;
  orders: Order[] = [];
  loadingMore = false;
  loading = false;
  noMore = false;
  filter = {};

  constructor(private orderService: OrderService) { }

  ngOnInit(): void {
    this.getAll();
  }

  ngAfterViewInit(): void {
    this.tooltip = MaterialService.initTooltip(this.tooltipRef);
  }

  ngOnDestroy(): void {
    this.tooltip.destroy();
    this.subscriber$.unsubscribe();
  }

  loadMore() {
    this.offset += STEP;
    this.loadingMore = true;
    this.getAll();
  }

  applyFilter(filter: Filter) {
    this.orders = [];
    this.offset = 0;
    this.loading = true;
    this.filter = filter;
    this.getAll();
  }

  isFiltered(): boolean {
    return Object.keys(this.filter).length > 0;
  }

  private getAll() {
    const params = Object.assign({}, this.filter, {
      offset: this.offset,
      limit: this.limit
    });
    this.subscriber$ = this.orderService.getAll(params).subscribe(
      orders => {
        this.orders = this.orders.concat(orders);
        this.noMore = orders.length < STEP;
        this.loadingMore = false;
        this.loading = false;
      },
      error => {
        MaterialService.toast('error');
        this.loadingMore = true;
      }
    );
  }
}
