import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from '../../services/user.service';
import { SessionService } from '../../services/session.service';
import { SubscriptionService } from '../../services/subscription.service';
import { Topic } from '../../interfaces/topic.interface';
import { Session } from '../../interfaces/session.interface';

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
})
export class MeComponent implements OnInit {
  public userForm: FormGroup;
  public topics!: Topic[];

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private sessionService: SessionService,
    private subscriptionService: SubscriptionService,
    private snackBar: MatSnackBar,
    private router: Router
  ) {
    const session = this.sessionService.getSession();
    this.userForm = this.fb.group({
      username: [session?.user.username || '', [Validators.required]],
      email: [
        session?.user.email || '',
        [Validators.required, Validators.email],
      ],
      password: [null, [Validators.pattern(this.userService.passwordRegex)]],
    });
  }

  ngOnInit(): void {
    this.getUserSubscriptions();
  }

  private getUserSubscriptions(): void {
    this.subscriptionService.getUserSubscriptions().subscribe({
      next: (response: Topic[]) => {
        this.topics = response;
      },
      error: (error: any) => {
        const message =
          error.error.message ||
          'Erreur lors de la récupération des abonnements';
        this.snackBar.open(message, '', {
          panelClass: ['bg-red-700'],
        });
      },
    });
  }

  public onSubmit() {
    if (this.userForm.valid) {
      const { username, email, password } = this.userForm.value;
      this.userService.updateUser(username, email, password).subscribe({
        next: (response: Session) => {
          this.sessionService.logIn(response);
          this.userForm.patchValue({ password: null });
          this.snackBar.open('Profil mis à jour avec succès', '', {
            panelClass: ['bg-green-700'],
          });
        },
        error: (error: any) => {
          const message =
            error.error.message || 'Erreur lors de la mise à jour du profil';
          this.snackBar.open(message, '', {
            panelClass: ['bg-red-700'],
          });
        },
      });
    }
  }

  public logout() {
    this.sessionService.logOut();
    this.router.navigate(['/login']);
  }
}
