import {
  Component,
  ElementRef,
  OnDestroy,
  OnInit,
  ViewChild
} from '@angular/core';
import {MaterialService} from '../shared/service/material/material.service';
import {OrderService} from '../shared/service/order/order.service';
import {Subscription} from 'rxjs';
import {Filter, Order} from '../shared/entities';

const STEP = 2;

@Component({
  selector: 'app-history-page',
  templateUrl: './history-page.component.html'
})
export class HistoryPageComponent implements OnInit, OnDestroy {
  @ViewChild('tooltip') tooltipRef: ElementRef;
  subscriber$: Subscription;
  isFilterVisible = false;
  orders: Order[] = [];
  loading = false;
  limit = STEP;
  filter = {};
  offset = 0;

  constructor(private orderService: OrderService) { }

  ngOnInit(): void {
    this.getAll();
  }

  ngOnDestroy(): void {
    this.subscriber$.unsubscribe();
  }

  applyFilter(filter: Filter) {
    this.orders = [];
    this.offset = 0;
    this.loading = true;
    this.filter = filter;
    this.getAll();
  }

  private getAll() {
    const params = Object.assign({}, this.filter, {
      offset: this.offset,
      limit: this.limit
    });
    this.subscriber$ = this.orderService.getAll(params).subscribe(
      orders => {
        this.orders = this.orders.concat(orders);
        this.loading = false;
      },
      MaterialService.error
    );
  }
}
