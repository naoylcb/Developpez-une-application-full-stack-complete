import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TopicService } from '../../services/topic.service';
import { Topic } from '../../interfaces/topic.interface';

@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
})
export class TopicsComponent implements OnInit {
  public topics!: Topic[];

  constructor(
    private topicService: TopicService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.topicService.getTopics().subscribe({
      next: (topics: Topic[]) => {
        this.topics = topics;
      },
      error: () =>
        this.snackBar.open('Erreur lors de la récupération des thèmes', '', {
          panelClass: ['bg-red-700'],
        }),
    });
  }
}
