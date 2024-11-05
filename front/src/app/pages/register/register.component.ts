import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from '../../services/user.service';
import { SessionService } from '../../services/session.service';
import { Session } from '../../interfaces/session.interface';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
})
export class RegisterComponent {
  public registerForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private sessionService: SessionService,
    private router: Router,
    private snackBar: MatSnackBar
  ) {
    this.registerForm = this.fb.group({
      username: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: [
        '',
        [
          Validators.required,
          Validators.pattern(this.userService.passwordRegex),
        ],
      ],
    });
  }

  public onSubmit() {
    if (this.registerForm.valid) {
      const { username, email, password } = this.registerForm.value;
      this.userService.register(username, email, password).subscribe({
        next: (response: Session) => {
          this.sessionService.logIn(response);
          this.snackBar.open('Inscription réussie', '', {
            panelClass: ['bg-green-700'],
          });
          this.router.navigate(['/feed']);
        },
        error: (error: any) => {
          const message = error.error.message || "Erreur lors de l'inscription";
          this.snackBar.open(message, '', {
            panelClass: ['bg-red-700'],
          });
        },
      });
    }
  }
}
