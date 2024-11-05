import { UserInfo } from './user-info.interface';

export interface Session {
  token: string;
  user: UserInfo;
}
