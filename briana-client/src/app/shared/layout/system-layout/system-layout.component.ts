import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {AuthService} from '../../service/auth/auth.service';
import {Router} from '@angular/router';
import {MaterialInstance} from '../../service/material/material.service';
import {LanguageService} from '../../service/language/language.service';
import {TranslationToken} from '../../service/language/languages';
import {SYSTEM_ROUTES} from './system.routes';

@Component({
  selector: 'app-system-layout',
  templateUrl: './system-layout.component.html',
  styleUrls: ['./system-layout.component.css']
})
export class SystemLayoutComponent implements OnInit {
  @ViewChild('dropdown') selectRef: ElementRef;
  select: MaterialInstance;
  links = SYSTEM_ROUTES;
  l: TranslationToken;

  constructor(private auth: AuthService,
              private router: Router,
              private lang: LanguageService) { }

  ngOnInit(): void {
    this.lang.get().subscribe(tokens => {
      this.l = tokens;
      this.links.map(link => {
        link.name = tokens[link.id];
        return link;
      });
    });
  }

  logout(event: Event) {
    event.preventDefault();
    this.auth.logout();
    this.router.navigate(['/login']);
  }
}


