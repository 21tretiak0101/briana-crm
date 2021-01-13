import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {CategoryService} from '../../shared/service/category/category.service';
import {switchMap} from 'rxjs/operators';
import {Observable, of} from 'rxjs';
import {MaterialService} from '../../shared/service/material/material.service';
import {Category} from '../../shared/entities';

@Component({
  selector: 'app-category-form',
  templateUrl: './category-form.component.html',
  styleUrls: ['./category-form.component.css']
})
export class CategoryFormComponent implements OnInit {
  isNew = true;
  image: File;
  imagePreview: string | ArrayBuffer;
  form: FormGroup;
  @ViewChild('inputElement') inputElement: ElementRef;
  category: Category;

  constructor(private route: ActivatedRoute,
              private categoryService: CategoryService,
              private router: Router) { }

  ngOnInit(): void {
    this.form = new FormGroup({
      name: new FormControl('', [Validators.required])
    });

    this.form.disable();

    this.route.params
      .pipe(
        switchMap(
          (params: Params) => {
            if (params['id']) {
              console.log(params);
              this.isNew = false;
              return this.categoryService.getById(params['id']);
            }
            return of(null);
          }
        )
      ).subscribe(
        (category: Category | null) => {
          if (category) {
            this.category = category;
            this.form.patchValue({
              name: category.name
            });
            this.imagePreview = category.imageSrc;
            MaterialService.updateInputs();
          }
          this.form.enable();
        },
      error => MaterialService.toast(error)
    );
  }

  onSubmit(): void {
    let observable$: Observable<Category>;
    this.form.disable();
    if (this.isNew) {
      observable$ = this.categoryService.create(
        this.form.value.name,
        this.image
      );
    } else {
      observable$ = this.categoryService.update(
        this.category.id,
        this.form.value.name,
        this.image
      );
    }
    observable$.subscribe(
      category => {
        this.category = category;
        MaterialService.toast('Изменения сохранены');
      },
      error => {
        MaterialService.toast(error);
        this.form.enable();
      }
    );
  }

  trigger(): void {
    this.inputElement.nativeElement.click();
  }

  onUpload(event: Event): void {
    const file = event.target['files'][0];
    this.image = file;
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    };
    reader.readAsDataURL(file);
  }

  delete(): void {
    const decision = window.confirm(`Удалить '${this.category.name}'?`);
    if (decision) {
      this.categoryService.delete(this.category.id);
      MaterialService.toast('удалено успешно');
      this.router.navigate(['/category']);
    }
  }
}
