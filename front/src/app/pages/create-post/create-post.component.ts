import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PostService } from '../../services/post.service';
import { TopicService } from '../../services/topic.service';
import { Topic } from '../../interfaces/topic.interface';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
})
export class CreatePostComponent implements OnInit {
  public postForm: FormGroup;
  public topics!: Topic[];

  constructor(
    private fb: FormBuilder,
    private postService: PostService,
    private topicService: TopicService,
    private snackBar: MatSnackBar,
    private router: Router
  ) {
    this.postForm = this.fb.group({
      title: ['', Validators.required],
      content: ['', Validators.required],
      topicId: [null, Validators.required],
    });
  }

  public ngOnInit(): void {
    this.getTopics();
  }

  public onSubmit() {
    if (this.postForm.valid) {
      const { title, content, topicId } = this.postForm.value;
      this.postService.createPost({ title, content, topicId }).subscribe({
        next: () => {
          this.snackBar.open('Article créé avec succès', '', {
            panelClass: ['bg-green-700'],
          });

          this.router.navigate(['/feed']);
        },
        error: (error: any) => {
          const message =
            error.error.message || "Erreur lors de la création de l'article";
          this.snackBar.open(message, '', {
            panelClass: ['bg-red-700'],
          });
        },
      });
    }
  }

  public getTopics(): void {
    this.topicService.getTopics().subscribe({
      next: (response: Topic[]) => {
        this.topics = response;
      },
      error: (error: any) => {
        const message =
          error.error.message || 'Erreur lors de la récupération des thèmes';
        this.snackBar.open(message, '', {
          panelClass: ['bg-red-700'],
        });
      },
    });
  }
}
