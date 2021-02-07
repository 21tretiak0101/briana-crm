import {Component, OnInit, ViewChild} from '@angular/core';
import {Client} from '../../shared/entities';
import {MaterialService} from '../../shared/service/material/material.service';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {ClientService} from '../../shared/service/client/client.service';
import {SuccessComponent} from '../../shared/component/success/success.component';
import {ResponsiveCircularLoaderComponent} from '../../shared/component/circular-loader/responsive-circular-loader/responsive-circular-loader.component';
import {Router} from '@angular/router';

@Component({
  selector: 'app-client-creation',
  templateUrl: './client-creation.component.html'
})
export class ClientCreationComponent implements OnInit {
  form: FormGroup;
  editable = true;
  @ViewChild(SuccessComponent)
  private successComponent: SuccessComponent;
  @ViewChild(ResponsiveCircularLoaderComponent)
  private circularLoader: ResponsiveCircularLoaderComponent;

  constructor(
    private clientService: ClientService,
    private builder: FormBuilder,
    private router: Router
  ) { }

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
    console.log(client);
    this.save(client);
  }

  save(client: Client): void {
    this.startLoading();
    this.clientService.save(client).subscribe(
      () => {
        this.success();
      },
      error => {
        MaterialService.error(error);
        this.endLoading();
      }
    );
  }

  private startLoading(): void {
    this.editable = false;
    this.circularLoader.startLoading();
  }

  private endLoading(): void {
    this.circularLoader.endLoading();
    this.editable = true;
  }

  private success(): void {
    this.circularLoader.endLoading();
    this.successComponent.success(() => {
      this.editable = true;
      this.router.navigate(['clients']);
    });
  }
}
