import {Component, OnInit, ViewChild} from '@angular/core';
import {Category} from '../../shared/entities';
import {FormBuilder, FormGroup} from '@angular/forms';
import {CreationHandlerComponent} from '../../shared/component/creation-handler/creation-handler.component';
import {CreationService} from '../../shared/service/creation/creation.service';
import {CategoryCreationService} from '../../shared/service/creation/category-creation/category-creation.service';

@Component({
  selector: 'app-category-creation',
  templateUrl: './category-creation.component.html',
  viewProviders: [{
    provide: CreationService,
    useClass: CategoryCreationService
  }]
})
export class CategoryCreationComponent implements OnInit {
  form: FormGroup;
  @ViewChild(CreationHandlerComponent)
  private creationHandler: CreationHandlerComponent;
  file: File;

  constructor(private builder: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.builder.group({
      name: this.builder.control(''),
      description: this.builder.control('')
    });
  }

  onSubmit(): void {
    const {name, description} = this.form.value;
    const category: Category = {name, description};
    this.creationHandler.save(category, this.file);
  }

  onFileRemove() {
    this.file = null;
  }

  onFileUpload(file: File) {
    this.file = file;
  }
}
