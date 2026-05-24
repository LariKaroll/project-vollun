import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class BasicAuthInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const username = 'seu_usuario';
    const password = 'sua_senha';
    const credentials = btoa(`${username}:${password}`);

    const authReq = req.clone({
      setHeaders: {
        Authorization: `Basic ${credentials}`
      }
    });

    return next.handle(authReq);
  }
}