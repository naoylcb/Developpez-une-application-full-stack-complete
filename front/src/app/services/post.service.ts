import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Post } from '../interfaces/post.interface';
import { Comment } from '../interfaces/comment.interface';

@Injectable({
  providedIn: 'root',
})
export class PostService {
  constructor(private http: HttpClient) {}

  public getFeed(): Observable<Post[]> {
    return this.http.get<Post[]>(`${environment.apiUrl}/feed`);
  }

  public getPost(id: number): Observable<Post> {
    return this.http.get<Post>(`${environment.apiUrl}/posts/${id}`);
  }

  public createPost(post: {
    title: string;
    content: string;
    topicId: number;
  }): Observable<Post> {
    return this.http.post<Post>(`${environment.apiUrl}/posts`, post);
  }

  public getComments(postId: number): Observable<Comment[]> {
    return this.http.get<Comment[]>(
      `${environment.apiUrl}/comments?postId=${postId}`
    );
  }

  public createComment(comment: {
    content: string;
    postId: number;
  }): Observable<Comment> {
    return this.http.post<Comment>(`${environment.apiUrl}/comments`, comment);
  }
}
