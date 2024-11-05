import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Session } from '../interfaces/session.interface';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  public passwordRegex: RegExp =
    /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!"#$%&'()*+,-./:;<=>?@\[\]^_`{|}~]).{8,}$/;

  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<Session> {
    return this.http.post<Session>(`${environment.apiUrl}/login`, {
      email,
      password,
    });
  }

  register(
    username: string,
    email: string,
    password: string
  ): Observable<Session> {
    return this.http.post<Session>(`${environment.apiUrl}/register`, {
      username,
      email,
      password,
    });
  }

  updateUser(
    username: string,
    email: string,
    password: string | null
  ): Observable<Session> {
    return this.http.put<Session>(`${environment.apiUrl}/me`, {
      username,
      email,
      ...(password && { password }),
    });
  }
}
