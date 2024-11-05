import { UserInfo } from './user-info.interface';
import { Topic } from './topic.interface';

export interface Post {
  id: number;
  title: string;
  content: string;
  author: UserInfo;
  topic: Topic;
  createdAt: Date;
}
