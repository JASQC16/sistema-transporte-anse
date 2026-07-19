import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cliente } from '../../models/cliente';
import { AuthService } from './auth.service';

@Injectable({ providedIn: 'root' })
export class ClienteService {
  private readonly API_URL = 'http://localhost:8080/api/clientes';

  constructor(private http: HttpClient, private authService: AuthService) {}

  private getHeaders(): HttpHeaders {
    const token = this.authService.getToken();
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  listar(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(this.API_URL, { headers: this.getHeaders() });
  }

  obtener(id: number): Observable<Cliente> {
    return this.http.get<Cliente>(`${this.API_URL}/${id}`, { headers: this.getHeaders() });
  }

  obtenerPorRuc(rucDni: string): Observable<Cliente> {
    return this.http.get<Cliente>(`${this.API_URL}/ruc/${rucDni}`, { headers: this.getHeaders() });
  }

  buscarPorRazonSocial(razonSocial: string): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(`${this.API_URL}/buscar?razonSocial=${razonSocial}`, { headers: this.getHeaders() });
  }

  crear(cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(this.API_URL, cliente, { headers: this.getHeaders() });
  }

  actualizar(id: number, cliente: Cliente): Observable<Cliente> {
    return this.http.put<Cliente>(`${this.API_URL}/${id}`, cliente, { headers: this.getHeaders() });
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`, { headers: this.getHeaders() });
  }
}