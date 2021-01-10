import {
  AfterViewInit,
  Component,
  ElementRef,
  EventEmitter,
  OnDestroy,
  OnInit,
  Output,
  ViewChild
} from '@angular/core';
import {
  MaterialInstance,
  MaterialService
} from '../../shared/service/material/material.service';
import {TranslationToken} from '../../shared/service/language/languages';
import {Client} from '../../shared/entities';
import {FormControl, FormGroup} from '@angular/forms';
import {ClientService} from '../../shared/service/client/client.service';
import {LanguageService} from '../../shared/service/language/language.service';
import {delay} from 'rxjs/operators';

export interface ClientModal {
  create(): void;
  update(client: Client): void;
}

@Component({
  selector: 'app-client-modal',
  templateUrl: './client-modal.component.html',
  styleUrls: ['./client-modal.component.css']
})
export class ClientModalComponent implements AfterViewInit, OnDestroy, OnInit {
  @ViewChild('modal') modalRef: ElementRef;
  @Output() initModalEvent = new EventEmitter<ClientModal>();
  @Output() updateClientEvent = new EventEmitter<Client>();
  l: TranslationToken;
  modal: MaterialInstance;
  form: FormGroup;
  isLoading = false;
  isSuccess = false;
  isNew = false;
  clientModal: ClientModal = {
    create: this.create.bind(this),
    update: this.update.bind(this)
  };

  constructor(private clientService: ClientService,
              private lang: LanguageService) { }

  ngOnInit(): void {
    this.initForm();
    this.l = this.lang.getCurrent();
  }

  ngAfterViewInit(): void {
    this.modal = MaterialService.initModal(this.modalRef);
    this.initModalEvent.emit(this.clientModal);
  }

  ngOnDestroy(): void {
    this.modal.destroy();
  }

  close() {
    this.modal.close();
  }

  submitForm() {
    const {
      name,
      email,
      phone,
      description,
      country,
      city,
      postcode
    } = this.form.value;
    const instance: Client = {
      name,
      email,
      phone,
      description,
      address: {
        country,
        city,
        postcode
      }
    };
    this.saveClient(instance);
  }

  private initForm() {
    this.form = new FormGroup({
      name: new FormControl(),
      email: new FormControl(),
      phone: new FormControl(),
      country: new FormControl(),
      city: new FormControl(),
      postcode: new FormControl(),
      description: new FormControl()
    });
  }

  private saveClient(client: Client): void {
    this.isLoading = true;
    this.clientService.save(client).pipe(delay(1000)).subscribe(
      saved => {
        if (!this.isNew) {
          this.updateClientEvent.emit(saved);
        }
        this.isLoading = false;
        this.success();
      },
      error => {
        MaterialService.toast(error);
        this.isLoading = true;
      }
    );
  }

  private success(): void {
    this.isSuccess = true;
    setTimeout(() => {
      this.isSuccess = false;
      this.close();
    }, 2500);
  }

  private create(): void {
    this.isNew = true;
    this.modal.open();
  }

  private update(client: Client): void {
    this.isNew = false;
    this.patchValues(client);
    this.modal.open();
  }

  private patchValues(client: Client): void {
    const {
      name,
      email,
      phone,
      description = '',
      address: {
        country,
        city,
        postcode
      },
    } = client;
    this.form.patchValue({
      name,
      email,
      phone,
      description,
      country,
      city,
      postcode
    });
    MaterialService.updateInputs();
  }
}
