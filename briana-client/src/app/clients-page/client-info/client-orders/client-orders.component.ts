import {Component, ElementRef, Input, ViewChild} from '@angular/core';
import {Order} from '../../../shared/entities';
import {MaterialService} from '../../../shared/service/material/material.service';

@Component({
  selector: 'app-client-orders',
  templateUrl: './client-orders.component.html',
  styleUrls: ['./client-orders.component.css']
})
export class ClientOrdersComponent {
  @Input() orders: Order[];
  @ViewChild('collapsible') set collapsibleRef(collapsibleRef: ElementRef) {
    if (collapsibleRef) {
      MaterialService.initCollapsible(collapsibleRef);
    }
  }
}
