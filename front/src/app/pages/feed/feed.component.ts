import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PostService } from '../../services/post.service';
import { Post } from '../../interfaces/post.interface';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
})
export class FeedComponent implements OnInit {
  public posts!: Post[];

  constructor(
    private postService: PostService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.postService.getFeed().subscribe({
      next: (response: Post[]) => {
        this.posts = response;
      },
      error: (error: any) => {
        const message =
          error.error.message || 'Erreur lors de la récupération des articles';
        this.snackBar.open(message, '', {
          panelClass: ['bg-red-700'],
        });
      },
    });
  }
}
