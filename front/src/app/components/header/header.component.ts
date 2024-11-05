import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SessionService } from '../../services/session.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
})
export class HeaderComponent {
  public sidenavOpened: boolean = false;

  constructor(private sessionService: SessionService, private router: Router) {}

  public isLogged(): boolean {
    return this.sessionService.isLogged();
  }

  public getCurrentUrl(): string {
    return this.router.url;
  }

  public toggleSidenav(): void {
    this.sidenavOpened = !this.sidenavOpened;
  }
}
