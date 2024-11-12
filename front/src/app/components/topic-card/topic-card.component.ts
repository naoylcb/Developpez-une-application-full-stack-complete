import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Topic } from '../../interfaces/topic.interface';

@Component({
  selector: 'app-topic-card',
  templateUrl: './topic-card.component.html',
})
export class TopicCardComponent {
  @Input() topic!: Topic;
  @Input() text!: string;
  @Output() action: EventEmitter<number> = new EventEmitter();

  constructor() {}

  public handleAction() {
    this.action.emit(this.topic.id);
  }
}
