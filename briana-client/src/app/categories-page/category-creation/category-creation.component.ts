import {Component} from '@angular/core';
import {Category} from '../../shared/entities';
import {CategoryService} from '../../shared/service/category/category.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-category-creation',
  templateUrl: './category-creation.component.html'
})
export class CategoryCreationComponent {
  file: File;

  constructor(private categoryService: CategoryService,
              private router: Router) { }

  onFileRemove() {
    this.file = null;
  }

  onFileUpload(file: File) {
    this.file = file;
  }

  onSubmit(category: Category) {
    this.categoryService.create(category, this.file)
      .subscribe(
        () => {
          this.router.navigate(['/categories']);
        }
      );
  }
}
