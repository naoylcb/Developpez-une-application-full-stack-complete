import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PostService } from '../../services/post.service';
import { Post } from '../../interfaces/post.interface';
import { Comment } from '../../interfaces/comment.interface';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
})
export class PostComponent implements OnInit {
  public post!: Post;
  public comments!: Comment[];
  public commentForm: FormGroup;

  constructor(
    private postService: PostService,
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    this.commentForm = this.fb.group({
      content: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.getPost();
  }

  private getPost(): void {
    const postId = Number(this.route.snapshot.paramMap.get('id'));
    this.postService.getPost(postId).subscribe({
      next: (post: Post) => {
        this.post = post;
        this.getComments();
      },
      error: (error: any) => {
        const message =
          error.error.message || "Erreur lors de la récupération de l'article";
        this.snackBar.open(message, '', {
          panelClass: ['bg-red-700'],
        });
      },
    });
  }

  public onSubmit(): void {
    if (this.commentForm.valid) {
      this.postService
        .createComment({
          content: this.commentForm.get('content')?.value,
          postId: this.post.id,
        })
        .subscribe({
          next: () => {
            this.getComments();
            this.commentForm.reset();
            this.snackBar.open('Commentaire ajouté avec succès', '', {
              panelClass: ['bg-green-700'],
            });
          },
          error: (error: any) => {
            const message =
              error.error.message ||
              'Erreur lors de la création du commentaire';
            this.snackBar.open(message, '', {
              panelClass: ['bg-red-700'],
            });
          },
        });
    }
  }

  public getComments(): void {
    this.postService.getComments(this.post.id).subscribe({
      next: (comments: Comment[]) => {
        this.comments = comments;
      },
      error: (error: any) => {
        const message =
          error.error.message ||
          'Erreur lors de la récupération des commentaires';
        this.snackBar.open(message, '', {
          panelClass: ['bg-red-700'],
        });
      },
    });
  }
}
