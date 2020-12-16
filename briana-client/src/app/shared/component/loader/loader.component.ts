import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-loader',
  template: `
    <div class="row">
      <div class="col s12">
        <div class="progress">
          <div class="indeterminate"></div>
        </div>
      </div>
    </div>
  `,
  styleUrls: ['./loader.component.css']
})
export class LoaderComponent implements OnInit {

  constructor() { }

  ngOnInit(): void { }

}
