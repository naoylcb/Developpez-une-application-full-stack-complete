import { UserInfo } from './user-info.interface';

export interface Comment {
  id: number;
  content: string;
  author: UserInfo;
}
