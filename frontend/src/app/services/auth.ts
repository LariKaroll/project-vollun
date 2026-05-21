import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { BehaviorSubject, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Auth {
  private readonly API = `${environment.apiUrl}/user/login`;
  private readonly API_REGISTER = `${environment.apiUrl}/user/register`;
  private readonly API_PDF = `${environment.apiUrl}/book/file`

  private usuarioSubject = new BehaviorSubject<any>(this.getUserLogado());
  
  public usuario$ = this.usuarioSubject.asObservable();

  constructor(private http: HttpClient) {
  }

  updatePdf(dadosPdf: any): Observable<any> {
    return this.http.post(this.API_PDF, dadosPdf, {responseType: 'text'});
  }

  register(dadosUsuario: any): Observable<any> {
    return this.http.post(this.API_REGISTER, dadosUsuario,{responseType : 'text' });
}

  logar(credenciais: any) {
    return this.http.post(this.API, credenciais, {responseType: 'json'}).pipe(
      tap((usuarioRetornado: any) => {
        
        localStorage.setItem('usuarioLogado', JSON.stringify(usuarioRetornado));
        this.usuarioSubject.next(usuarioRetornado);
      })
    );
  }

  logout() {
    localStorage.removeItem('usuarioLogado');
    this.usuarioSubject.next(null);
  }

  getUserLogado() {
  const user = localStorage.getItem('usuarioLogado'); 
  return user ? JSON.parse(user) : null;
  }
}


