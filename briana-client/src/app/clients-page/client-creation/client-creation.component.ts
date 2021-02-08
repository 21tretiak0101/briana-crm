import {Component, OnInit, ViewChild} from '@angular/core';
import {Client} from '../../shared/entities';
import {FormControl, FormGroup} from '@angular/forms';
import {CreationService} from '../../shared/service/creation/creation.service';
import {ClientCreationService} from '../../shared/service/creation/client-creation/client-creation.service';
import {CreationHandlerComponent} from '../../shared/component/creation-handler/creation-handler.component';

@Component({
  selector: 'app-client-creation',
  templateUrl: './client-creation.component.html',
  viewProviders: [{
    provide: CreationService,
    useClass: ClientCreationService
  }]
})
export class ClientCreationComponent implements OnInit {
  @ViewChild(CreationHandlerComponent)
  private creationHandler: CreationHandlerComponent;
  form: FormGroup;

  ngOnInit(): void {
    this.form = new FormGroup({
      name: new FormControl(),
      email: new FormControl(),
      phone: new FormControl(),
      description: new FormControl(),
      address: new FormGroup({
        country: new FormControl(),
        city: new FormControl(),
        postcode: new FormControl()
      })
    });
  }

  onSubmit() {
    const {name, email, phone, description} = this.form.value;
    const {country, city, postcode} = this.form.value.address;
    const client: Client = {
      name,
      email,
      phone,
      description,
      address: {country, city, postcode}
    };
    this.creationHandler.save(client);
  }
}
