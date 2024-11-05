import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from '../../services/user.service';
import { SessionService } from '../../services/session.service';
import { Session } from '../../interfaces/session.interface';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
})
export class LoginComponent {
  public loginForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private sessionService: SessionService,
    private snackBar: MatSnackBar,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }

  public onSubmit() {
    if (this.loginForm.valid) {
      const { email, password } = this.loginForm.value;
      this.userService.login(email, password).subscribe({
        next: (response: Session) => {
          this.sessionService.logIn(response);
          this.snackBar.open('Connexion réussie', '', {
            panelClass: ['bg-green-700'],
          });
          this.router.navigate(['/feed']);
        },
        error: (error: any) => {
          const message = error.error.message || 'Erreur lors de la connexion';
          this.snackBar.open(message, '', {
            panelClass: ['bg-red-700'],
          });
        },
      });
    }
  }
}
