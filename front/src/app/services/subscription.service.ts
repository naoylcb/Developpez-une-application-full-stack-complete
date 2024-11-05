import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Topic } from '../interfaces/topic.interface';

@Injectable({
  providedIn: 'root',
})
export class SubscriptionService {
  constructor(private http: HttpClient) {}

  public getUserSubscriptions(): Observable<Topic[]> {
    return this.http.get<Topic[]>(`${environment.apiUrl}/subscriptions/me`);
  }

  public subscribeToTopic(topicId: number): Observable<void> {
    return this.http.post<void>(
      `${environment.apiUrl}/subscriptions?topicId=${topicId}`,
      {}
    );
  }

  public unsubscribeFromTopic(topicId: number): Observable<void> {
    return this.http.delete<void>(
      `${environment.apiUrl}/subscriptions?topicId=${topicId}`
    );
  }
}
