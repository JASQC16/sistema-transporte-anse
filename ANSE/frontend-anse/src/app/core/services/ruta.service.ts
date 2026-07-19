import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ruta } from '../../models/ruta';
import { AuthService } from './auth.service';

@Injectable({ providedIn: 'root' })
export class RutaService {
  private readonly API_URL = 'http://localhost:8080/api/rutas';

  constructor(private http: HttpClient, private authService: AuthService) {}

  private getHeaders(): HttpHeaders {
    const token = this.authService.getToken();
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  listar(): Observable<Ruta[]> {
    return this.http.get<Ruta[]>(this.API_URL, { headers: this.getHeaders() });
  }

  obtener(id: number): Observable<Ruta> {
    return this.http.get<Ruta>(`${this.API_URL}/${id}`, { headers: this.getHeaders() });
  }

  buscarPorOrigen(origen: string): Observable<Ruta[]> {
    return this.http.get<Ruta[]>(`${this.API_URL}/origen?origen=${origen}`, { headers: this.getHeaders() });
  }

  buscarPorDestino(destino: string): Observable<Ruta[]> {
    return this.http.get<Ruta[]>(`${this.API_URL}/destino?destino=${destino}`, { headers: this.getHeaders() });
  }

  crear(ruta: Ruta): Observable<Ruta> {
    return this.http.post<Ruta>(this.API_URL, ruta, { headers: this.getHeaders() });
  }

  actualizar(id: number, ruta: Ruta): Observable<Ruta> {
    return this.http.put<Ruta>(`${this.API_URL}/${id}`, ruta, { headers: this.getHeaders() });
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`, { headers: this.getHeaders() });
  }
}