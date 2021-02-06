import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Category} from '../../../shared/entities';
import {FormBuilder, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-category-details',
  templateUrl: './category-details.component.html',
  styleUrls: ['./category-details.component.css']
})
export class CategoryDetailsComponent implements OnInit {
  @Output() submitEvent = new EventEmitter<Category>();
  form: FormGroup;

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
    this.submitEvent.emit(category);
  }
}
