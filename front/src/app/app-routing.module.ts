import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';
import { UnauthGuard } from './guards/unauth.guard';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { FeedComponent } from './pages/feed/feed.component';
import { MeComponent } from './pages/me/me.component';
import { TopicsComponent } from './pages/topics/topics.component';
import { PostComponent } from './pages/post/post.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { CreatePostComponent } from './pages/create-post/create-post.component';

const routes: Routes = [
  { path: '', canActivate: [UnauthGuard], component: HomeComponent },
  { path: 'login', canActivate: [UnauthGuard], component: LoginComponent },
  {
    path: 'register',
    canActivate: [UnauthGuard],
    component: RegisterComponent,
  },
  { path: 'feed', canActivate: [AuthGuard], component: FeedComponent },
  { path: 'me', canActivate: [AuthGuard], component: MeComponent },
  { path: 'topics', canActivate: [AuthGuard], component: TopicsComponent },
  { path: 'post/:id', canActivate: [AuthGuard], component: PostComponent },
  {
    path: 'create-post',
    canActivate: [AuthGuard],
    component: CreatePostComponent,
  },
  { path: '404', component: NotFoundComponent },
  { path: '**', redirectTo: '404' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
