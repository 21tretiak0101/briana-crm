import {
  Component,
  ElementRef,
  Input,
  OnDestroy,
  ViewChild
} from '@angular/core';
import {Order} from '../../shared/entities';
import {
  MaterialInstance,
  MaterialService
} from '../../shared/service/material/material.service';

@Component({
  selector: 'app-history-list',
  templateUrl: './history-list.component.html',
  styleUrls: ['./history-list.component.css']
})
export class HistoryListComponent implements OnDestroy {
  @Input() orders: Order[];
  modal: MaterialInstance;
  selectedOrder: Order;

  constructor() { }

  @ViewChild('collapsible') set collapsible(collapsibleRef: ElementRef) {
    if (collapsibleRef) {
      MaterialService.initCollapsible(collapsibleRef);
    }
  }

  ngOnDestroy(): void {
    this.modal.destroy();
  }

  computePrice(order: Order): number {
    return order.products.reduce((total, product) => {
      total = total + product.price;
      return total;
    }, 0);
  }

  select(order: Order) {
    this.selectedOrder = order;
    this.modal.open();
  }

  closeModal() {
    this.modal.close();
  }
}
