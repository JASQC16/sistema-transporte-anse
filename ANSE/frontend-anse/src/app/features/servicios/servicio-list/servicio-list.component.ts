import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { ServicioService } from '../../../core/services/servicio.service';
import { AuthService } from '../../../core/services/auth.service';
import { Servicio } from '../../../models/servicio';

@Component({
  selector: 'app-servicio-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './servicio-list.component.html',
  styleUrl: './servicio-list.component.css'
})
export class ServicioListComponent implements OnInit {
  servicios: Servicio[] = [];
  isAdmin = false;

  constructor(
    private servicioService: ServicioService,
    private authService: AuthService
  ) {
    this.isAdmin = this.authService.isAdmin();
  }

  ngOnInit(): void {
    this.cargarServicios();
  }

  cargarServicios(): void {
    this.servicioService.listar().subscribe(data => this.servicios = data);
  }

  eliminar(id: number | undefined): void {
    if (!id) return;
    if (confirm('¿Eliminar este servicio?')) {
      this.servicioService.eliminar(id).subscribe(() => this.cargarServicios());
    }
  }
}