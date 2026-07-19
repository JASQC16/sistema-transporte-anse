import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Mantenimiento } from '../../models/mantenimiento';
import { AuthService } from './auth.service';

@Injectable({ providedIn: 'root' })
export class MantenimientoService {
  private readonly API_URL = 'http://localhost:8080/api/mantenimientos';

  constructor(private http: HttpClient, private authService: AuthService) {}

  private getHeaders(): HttpHeaders {
    const token = this.authService.getToken();
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  listar(): Observable<Mantenimiento[]> {
    return this.http.get<Mantenimiento[]>(this.API_URL, { headers: this.getHeaders() });
  }

  obtener(id: number): Observable<Mantenimiento> {
    return this.http.get<Mantenimiento>(`${this.API_URL}/${id}`, { headers: this.getHeaders() });
  }

  listarPorVehiculo(vehiculoId: number): Observable<Mantenimiento[]> {
    return this.http.get<Mantenimiento[]>(`${this.API_URL}/vehiculo/${vehiculoId}`, { headers: this.getHeaders() });
  }

  listarPorFechas(inicio: string, fin: string): Observable<Mantenimiento[]> {
    return this.http.get<Mantenimiento[]>(`${this.API_URL}/fechas?inicio=${inicio}&fin=${fin}`, { headers: this.getHeaders() });
  }

  crear(mantenimiento: Mantenimiento): Observable<Mantenimiento> {
    return this.http.post<Mantenimiento>(this.API_URL, mantenimiento, { headers: this.getHeaders() });
  }

  actualizar(id: number, mantenimiento: Mantenimiento): Observable<Mantenimiento> {
    return this.http.put<Mantenimiento>(`${this.API_URL}/${id}`, mantenimiento, { headers: this.getHeaders() });
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`, { headers: this.getHeaders() });
  }
}