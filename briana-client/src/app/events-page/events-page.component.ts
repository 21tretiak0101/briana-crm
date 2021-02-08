import {Event} from '../shared/entities';
import {Observable} from 'rxjs';
import {Component, OnInit} from '@angular/core';
import {EventService} from '../shared/service/event/event.service';

@Component({
  selector: 'app-events-page',
  templateUrl: './events-page.component.html',
  styleUrls: ['./events-page.component.css']
})
export class EventsPageComponent implements OnInit {
  events$: Observable<Event[]>;

  constructor(private eventService: EventService) { }

  ngOnInit(): void {
    this.events$ = this.eventService.getAll();
  }
}
