import {
  AfterViewInit,
  Component,
  ElementRef,
  EventEmitter,
  OnDestroy,
  Output,
  ViewChild
} from '@angular/core';
import {Filter} from '../../shared/entities';
import {
  Datepicker,
  MaterialService
} from '../../shared/service/material/material.service';

@Component({
  selector: 'app-history-filter',
  templateUrl: './history-filter.component.html'
})
export class HistoryFilterComponent implements OnDestroy, AfterViewInit {
  @ViewChild('start') startRef: ElementRef;
  @ViewChild('end') endRef: ElementRef;
  @Output() filterEventEmitter = new EventEmitter<Filter>();
  startDatepicker: Datepicker;
  endDatepicker: Datepicker;
  orderNumber: number;
  isValid = true;

  ngOnDestroy(): void {
    this.startDatepicker.destroy();
    this.endDatepicker.destroy();
  }

  ngAfterViewInit(): void {
    this.startDatepicker = MaterialService.initDatepicker(
      this.startRef,
      this.validate.bind(this)
    );
    this.endDatepicker = MaterialService.initDatepicker(
      this.endRef,
      this.validate.bind(this)
    );
  }

  validate() {
    const start = this.startDatepicker.date;
    const end = this.endDatepicker.date;
    this.isValid = start && end ? start < end : true;
  }

  submit() {
    const filter: Filter = {};
    filter.order = this.orderNumber || filter.order;
    filter.start = this.startDatepicker.date || filter.start;
    filter.end = this.endDatepicker.date || filter.end;
    this.filterEventEmitter.emit(filter);
  }
}
