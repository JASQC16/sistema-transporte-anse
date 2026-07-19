import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Servicio } from '../../models/servicio';
import { AuthService } from './auth.service';

@Injectable({ providedIn: 'root' })
export class ServicioService {
  private readonly API_URL = 'http://localhost:8080/api/servicios';

  constructor(private http: HttpClient, private authService: AuthService) {}

  private getHeaders(): HttpHeaders {
    const token = this.authService.getToken();
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  listar(): Observable<Servicio[]> {
    return this.http.get<Servicio[]>(this.API_URL, { headers: this.getHeaders() });
  }

  obtener(id: number): Observable<Servicio> {
    return this.http.get<Servicio>(`${this.API_URL}/${id}`, { headers: this.getHeaders() });
  }

  listarPorEstado(estado: string): Observable<Servicio[]> {
    return this.http.get<Servicio[]>(`${this.API_URL}/estado/${estado}`, { headers: this.getHeaders() });
  }

  listarPorFechas(inicio: string, fin: string): Observable<Servicio[]> {
    return this.http.get<Servicio[]>(`${this.API_URL}/fechas?inicio=${inicio}&fin=${fin}`, { headers: this.getHeaders() });
  }

  listarPorCliente(clienteId: number): Observable<Servicio[]> {
    return this.http.get<Servicio[]>(`${this.API_URL}/cliente/${clienteId}`, { headers: this.getHeaders() });
  }

  crear(servicio: Servicio): Observable<Servicio> {
    return this.http.post<Servicio>(this.API_URL, servicio, { headers: this.getHeaders() });
  }

  actualizar(id: number, servicio: Servicio): Observable<Servicio> {
    return this.http.put<Servicio>(`${this.API_URL}/${id}`, servicio, { headers: this.getHeaders() });
  }

  actualizarEstado(id: number, estado: string): Observable<Servicio> {
    return this.http.patch<Servicio>(`${this.API_URL}/${id}/estado?estado=${estado}`, null, { headers: this.getHeaders() });
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`, { headers: this.getHeaders() });
  }
}