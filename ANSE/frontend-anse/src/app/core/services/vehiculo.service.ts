import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Vehiculo } from '../../models/vehiculo';
import { AuthService } from './auth.service';

@Injectable({ providedIn: 'root' })
export class VehiculoService {
  private readonly API_URL = 'http://localhost:8080/api/vehiculos';

  constructor(private http: HttpClient, private authService: AuthService) {}

  private getHeaders(): HttpHeaders {
    const token = this.authService.getToken();
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  listar(): Observable<Vehiculo[]> {
    return this.http.get<Vehiculo[]>(this.API_URL, { headers: this.getHeaders() });
  }

  listarDisponibles(): Observable<Vehiculo[]> {
    return this.http.get<Vehiculo[]>(`${this.API_URL}/disponibles`, { headers: this.getHeaders() });
  }

  obtener(id: number): Observable<Vehiculo> {
    return this.http.get<Vehiculo>(`${this.API_URL}/${id}`, { headers: this.getHeaders() });
  }

  obtenerPorPlaca(placa: string): Observable<Vehiculo> {
    return this.http.get<Vehiculo>(`${this.API_URL}/placa/${placa}`, { headers: this.getHeaders() });
  }

  buscarPorEstado(estado: string): Observable<Vehiculo[]> {
    return this.http.get<Vehiculo[]>(`${this.API_URL}/estado/${estado}`, { headers: this.getHeaders() });
  }

  crear(vehiculo: Vehiculo): Observable<Vehiculo> {
    return this.http.post<Vehiculo>(this.API_URL, vehiculo, { headers: this.getHeaders() });
  }

  actualizar(id: number, vehiculo: Vehiculo): Observable<Vehiculo> {
    return this.http.put<Vehiculo>(`${this.API_URL}/${id}`, vehiculo, { headers: this.getHeaders() });
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`, { headers: this.getHeaders() });
  }
}