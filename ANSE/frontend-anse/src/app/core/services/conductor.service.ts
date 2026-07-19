import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Conductor } from '../../models/conductor';
import { AuthService } from './auth.service';

@Injectable({ providedIn: 'root' })
export class ConductorService {
  private readonly API_URL = 'http://localhost:8080/api/conductores';

  constructor(private http: HttpClient, private authService: AuthService) {}

  private getHeaders(): HttpHeaders {
    const token = this.authService.getToken();
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  listar(): Observable<Conductor[]> {
    return this.http.get<Conductor[]>(this.API_URL, { headers: this.getHeaders() });
  }

  listarDisponibles(): Observable<Conductor[]> {
    return this.http.get<Conductor[]>(`${this.API_URL}/disponibles`, { headers: this.getHeaders() });
  }

  obtener(id: number): Observable<Conductor> {
    return this.http.get<Conductor>(`${this.API_URL}/${id}`, { headers: this.getHeaders() });
  }

  obtenerPorLicencia(licencia: string): Observable<Conductor> {
    return this.http.get<Conductor>(`${this.API_URL}/licencia/${licencia}`, { headers: this.getHeaders() });
  }

  buscarPorNombres(nombres: string): Observable<Conductor[]> {
    return this.http.get<Conductor[]>(`${this.API_URL}/buscar?nombres=${nombres}`, { headers: this.getHeaders() });
  }

  crear(conductor: Conductor): Observable<Conductor> {
    return this.http.post<Conductor>(this.API_URL, conductor, { headers: this.getHeaders() });
  }

  actualizar(id: number, conductor: Conductor): Observable<Conductor> {
    return this.http.put<Conductor>(`${this.API_URL}/${id}`, conductor, { headers: this.getHeaders() });
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`, { headers: this.getHeaders() });
  }
}