import { Component } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { Router } from '@angular/router';
import { Auth } from '../../../services/auth';

@Component({
  selector: 'app-register',
  imports: [FormsModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {
  novoUsuario = {
    name: '',
    username: '',
    email: '',
    password: '',
    cpf: ''
  };
  constructor(
      private auth: Auth, 
      private router: Router
    ) {}

    efetuarRegistro() {
      
      this.auth.register(this.novoUsuario).subscribe({
        next: (res) => {
          console.log("Usuário registrado com sucesso!", res);
          alert("Conta criada! Agora faça seu login.");
          this.router.navigate(['/login']);
        },
        error: (err) => {
          console.error("Erro ao registrar:", err);
          alert("Erro ao criar conta. Verifique os logs do servidor.");
          }
      });
    }
}
