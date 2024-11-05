import { Injectable } from '@angular/core';
import { Session } from '../interfaces/session.interface';

@Injectable({
  providedIn: 'root',
})
export class SessionService {
  public logIn(session: Session): void {
    localStorage.setItem('session', JSON.stringify(session));
  }

  public logOut(): void {
    localStorage.removeItem('session');
  }

  public getSession(): Session | undefined {
    const session: string | null = localStorage.getItem('session');
    if (session) {
      return JSON.parse(session);
    }
    return undefined;
  }

  public isLogged(): boolean {
    return this.getSession() !== undefined;
  }
}
