import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Categoria } from '../../models/categoria';
import { AuthService } from './auth.service';

@Injectable({ providedIn: 'root' })
export class CategoriaService {
  private readonly API_URL = 'http://localhost:8080/api/categorias';

  constructor(private http: HttpClient, private authService: AuthService) {}

  private getHeaders(): HttpHeaders {
    const token = this.authService.getToken();
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  listar(): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(this.API_URL, { headers: this.getHeaders() });
  }

  obtener(id: number): Observable<Categoria> {
    return this.http.get<Categoria>(`${this.API_URL}/${id}`, { headers: this.getHeaders() });
  }

  crear(categoria: Categoria): Observable<Categoria> {
    return this.http.post<Categoria>(this.API_URL, categoria, { headers: this.getHeaders() });
  }

  actualizar(id: number, categoria: Categoria): Observable<Categoria> {
    return this.http.put<Categoria>(`${this.API_URL}/${id}`, categoria, { headers: this.getHeaders() });
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`, { headers: this.getHeaders() });
  }

  buscarPorNombre(nombre: string): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(`${this.API_URL}/buscar?nombre=${nombre}`, { headers: this.getHeaders() });
  }
}