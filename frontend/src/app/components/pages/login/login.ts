import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Auth } from '../../../services/auth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  constructor(
    private auth: Auth, 
    private router: Router
  ) {}

  onSubmit(dados: any) {
    this.auth.logar(dados).subscribe({
      next: (res: any) => {
        alert('Login realizado com sucesso!');
        this.router.navigate(['/dashboard'])
      },
      error: (err: { error: string; }) => {
          alert('Falha no login: ' + err.error);
      }
    });
  }
}
