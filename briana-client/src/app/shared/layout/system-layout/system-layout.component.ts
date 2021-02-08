import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../service/auth/auth.service';
import {Router} from '@angular/router';
import {LanguageService} from '../../service/language/language.service';
import {LanguageTokens} from '../../service/language/languages';
import {SYSTEM_ROUTES} from './system.routes';
import {Employee} from '../../entities';
import {DEFAULT_AVATAR_PATH} from '../../../../environments/environment';

@Component({
  selector: 'app-system-layout',
  templateUrl: './system-layout.component.html',
  styleUrls: ['./system-layout.component.css']
})
export class SystemLayoutComponent implements OnInit {
  routes = SYSTEM_ROUTES;
  tokens: LanguageTokens;
  authenticated: Employee;
  defaultAvatar = DEFAULT_AVATAR_PATH;

  constructor(
    private languageService: LanguageService,
    private authService: AuthService,
    private router: Router
  ) {
    this.authenticated = this.authService.authenticated;
  }

  ngOnInit(): void {
    this.languageService.tokens.subscribe(tokens => {
      this.tokens = tokens;
      this.routes.map(link => {
        link.name = tokens[link.id];
        return link;
      });
    });
    this.authService.authenticatedSub.subscribe(authenticated => {
      this.authenticated = authenticated;
    });
  }

  logout(event: Event) {
    event.preventDefault();
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}


