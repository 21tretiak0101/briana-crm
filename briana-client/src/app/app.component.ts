import { Component } from '@angular/core';
import {TestService} from '../service/test.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private service: TestService) { }

  title = 'briana-client';
  hello = '';

  click() {
    this.service.getMessage().subscribe((message) => {
      console.log(message);
      this.hello = message.response;
    })
  }
}
