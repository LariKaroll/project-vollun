import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Auth {
  private readonly API = `${environment.apiUrl}/user/login`;
  private readonly API_REGISTER = `${environment.apiUrl}/user/register`;

  constructor(private http: HttpClient) {
  }

  register(dadosUsuario: any): Observable<any> {
  return this.http.post(this.API_REGISTER, dadosUsuario,{responseType : 'text' });
}

  logar(credenciais: any) {
    return this.http.post(this.API, credenciais).pipe(
      tap((usuarioRetornado: any) => {
        
        localStorage.setItem('usuarioLogado', JSON.stringify(usuarioRetornado));
      })
    );
  }

  logout() {
    localStorage.removeItem('usuarioLogado');
  }

  getUserLogado() {
  const user = localStorage.getItem('usuarioLogado'); 
  return user ? JSON.parse(user) : null;
  }
}


