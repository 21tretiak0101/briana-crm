import {Component, EventEmitter, Input, Output, ViewChild} from '@angular/core';
import {Client} from '../../shared/entities';
import {ModalHandlerComponent} from '../../shared/component/modal-handler/modal-handler.component';
import {
  DynamicFormGroupValues,
  DynamicQuestion,
  DynamicQuestionGroup
} from '../../shared/component/dynamic-question/dynamic-question.component';
import {ClientService} from '../../shared/service/client/client.service';
import {MaterialService} from '../../shared/service/material/material.service';

@Component({
  selector: 'app-client-modal',
  template: `
    <app-modal-handler
      (submitEvent)="onSubmit($event)"
      [questionGroups]="dynamicQuestionGroup"
    ></app-modal-handler>
  `
})
export class ClientModalComponent {
  @Input() client: Client;
  @Output() updateClientEvent = new EventEmitter<Client>();
  @ViewChild(ModalHandlerComponent)
  private modalHandler: ModalHandlerComponent;

  private clientGroupName = 'client';
  private addressGroupName = 'address';

  dynamicQuestionGroup: DynamicQuestionGroup[] = [
    {
      groupName: this.clientGroupName,
      questions: [
        new DynamicQuestion({
          key: 'name',
          label: 'name',
          type: 'text'
        }),
        new DynamicQuestion({
          key: 'email',
          label: 'email',
          type: 'text'
        }),
        new DynamicQuestion({
          key: 'phone',
          label: 'phone',
          type: 'text'
        }),
        new DynamicQuestion({
          key: 'description',
          label: 'desc',
          type: 'text'
        })
      ]
    },
    {
      groupName: this.addressGroupName,
      questions: [
        new DynamicQuestion({
          key: 'country',
          label: 'country',
          type: 'text'
        }),
        new DynamicQuestion({
          key: 'city',
          label: 'city',
          type: 'text'
        }),
        new DynamicQuestion({
          key: 'postcode',
          label: 'postcode',
          type: 'text'
        }),
      ]
    }
  ];

  constructor(private clientService: ClientService) { }

  open(): void {
    const {name, email, phone, description} = this.client;
    this.modalHandler.open({
      [this.clientGroupName]: {name, email, phone, description},
      [this.addressGroupName]: {
        city: this.client.address?.city,
        country: this.client.address?.country,
        postcode: this.client.address?.postcode
      }
    });
  }

  onSubmit(values: DynamicFormGroupValues) {
    const {name, email, phone, description} = values[this.clientGroupName];
    const {city, country, postcode} = values[this.addressGroupName];
    const client: Client = {
      name,
      email,
      phone,
      description,
      address: {
        city,
        country,
        postcode
      }
    };
    this.update(client);
  }

  private update(client: Client): void {
    this.clientService.save(client).subscribe(
      saved => {
          this.updateClientEvent.emit(saved);
          this.modalHandler.success();
      },
      error => {
        this.modalHandler.error();
        MaterialService.toast(error);
      }
    );
  }
}
