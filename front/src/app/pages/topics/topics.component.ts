import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TopicService } from '../../services/topic.service';
import { SubscriptionService } from '../../services/subscription.service';
import { Topic } from '../../interfaces/topic.interface';

@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
})
export class TopicsComponent implements OnInit {
  public topics!: Topic[];
  public topicButtonText: string = "S'abonner";

  constructor(
    private topicService: TopicService,
    private subscriptionService: SubscriptionService,
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

  public subscribe(topicId: number) {
    this.subscriptionService.subscribeToTopic(topicId).subscribe({
      next: () =>
        this.snackBar.open('Vous êtes abonné à ce thème', '', {
          panelClass: ['bg-green-700'],
        }),
      error: (error: any) => {
        const message = error.error.message || "Erreur lors de l'abonnement";
        this.snackBar.open(message, '', {
          panelClass: ['bg-red-700'],
        });
      },
    });
  }
}
