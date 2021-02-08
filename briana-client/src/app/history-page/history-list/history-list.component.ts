import {Component, ElementRef, Input, ViewChild} from '@angular/core';
import {Order} from '../../shared/entities';
import {MaterialService} from '../../shared/service/material/material.service';

@Component({
  selector: 'app-history-list',
  templateUrl: './history-list.component.html',
  styleUrls: ['./history-list.component.css']
})
export class HistoryListComponent {
  @Input() orders: Order[];

  @ViewChild('collapsible') set collapsible(collapsibleRef: ElementRef) {
    if (collapsibleRef) {
      MaterialService.initCollapsible(collapsibleRef);
    }
  }
}
