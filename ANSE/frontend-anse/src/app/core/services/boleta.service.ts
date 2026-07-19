import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Boleta } from '../../models/boleta';
import { AuthService } from './auth.service';

@Injectable({ providedIn: 'root' })
export class BoletaService {
  private readonly API_URL = 'http://localhost:8080/api/boletas';

  constructor(private http: HttpClient, private authService: AuthService) {}

  private getHeaders(): HttpHeaders {
    const token = this.authService.getToken();
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  listar(): Observable<Boleta[]> {
    return this.http.get<Boleta[]>(this.API_URL, { headers: this.getHeaders() });
  }

  obtener(id: number): Observable<Boleta> {
    return this.http.get<Boleta>(`${this.API_URL}/${id}`, { headers: this.getHeaders() });
  }

  obtenerPorServicio(servicioId: number): Observable<Boleta> {
    return this.http.get<Boleta>(`${this.API_URL}/servicio/${servicioId}`, { headers: this.getHeaders() });
  }

  listarPorFechas(inicio: string, fin: string): Observable<Boleta[]> {
    return this.http.get<Boleta[]>(`${this.API_URL}/fechas?inicio=${inicio}&fin=${fin}`, { headers: this.getHeaders() });
  }

  crear(boleta: Boleta): Observable<Boleta> {
    return this.http.post<Boleta>(this.API_URL, boleta, { headers: this.getHeaders() });
  }

  actualizar(id: number, boleta: Boleta): Observable<Boleta> {
    return this.http.put<Boleta>(`${this.API_URL}/${id}`, boleta, { headers: this.getHeaders() });
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`, { headers: this.getHeaders() });
  }
}