import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const userJson = localStorage.getItem('usuarioLogado');

  if (userJson) {
    const user = JSON.parse(userJson);
    const token = user?.token; 

    if (token) {
      const authReq = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
      
      return next(authReq);
    }
  }

  return next(req);
};