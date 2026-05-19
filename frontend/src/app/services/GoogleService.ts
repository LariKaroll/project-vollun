import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GoogleService {
  
  private readonly API = `${environment.apiUrl}/book/search-external`;

  constructor(private http: HttpClient) {}

  buscarNoGoogle(termo: string): Observable<any[]> {
    const params = new HttpParams().set('termo', termo);
    
    return this.http.get<any>(this.API, { params }).pipe(
      map(response => response.items || [])
    );
  }
}