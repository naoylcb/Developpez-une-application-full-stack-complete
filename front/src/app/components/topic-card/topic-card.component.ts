import { Component, Input } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SubscriptionService } from '../../services/subscription.service';
import { Topic } from '../../interfaces/topic.interface';

@Component({
  selector: 'app-topic-card',
  templateUrl: './topic-card.component.html',
})
export class TopicCardComponent {
  @Input() topic!: Topic;
  @Input() isSubscribed!: boolean;

  constructor(
    private subscriptionService: SubscriptionService,
    private snackBar: MatSnackBar
  ) {}

  public subscribe() {
    this.subscriptionService.subscribeToTopic(this.topic.id).subscribe({
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

  public unsubscribe() {
    this.subscriptionService.unsubscribeFromTopic(this.topic.id).subscribe({
      next: () => {
        this.snackBar.open('Vous êtes désabonné de ce thème', '', {
          panelClass: ['bg-green-700'],
        });

        window.location.reload();
      },
      error: (error: any) => {
        const message = error.error.message || 'Erreur lors du désabonnement';
        this.snackBar.open(message, '', {
          panelClass: ['bg-red-700'],
        });
      },
    });
  }
}
