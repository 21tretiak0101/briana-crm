import { Component } from '@angular/core';
import {BookService} from '../service/book.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private service: BookService) { }

  title = 'briana-client';
  hello = '';

  click() {
    this.service.getHello().subscribe((message) => {
      console.log(message);
      this.hello = message.response;
    })
  }
}
