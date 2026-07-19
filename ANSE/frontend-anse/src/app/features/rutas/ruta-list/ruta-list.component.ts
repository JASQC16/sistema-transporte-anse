import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { RutaService } from '../../../core/services/ruta.service';
import { AuthService } from '../../../core/services/auth.service';
import { Ruta } from '../../../models/ruta';

@Component({
  selector: 'app-ruta-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './ruta-list.component.html',
  styleUrl: './ruta-list.component.css'
})
export class RutaListComponent implements OnInit {
  rutas: Ruta[] = [];
  isAdmin = false;

  constructor(
    private rutaService: RutaService,
    private authService: AuthService
  ) {
    this.isAdmin = this.authService.isAdmin();
  }

  ngOnInit(): void {
    this.cargarRutas();
  }

  cargarRutas(): void {
    this.rutaService.listar().subscribe(data => this.rutas = data);
  }

  eliminar(id: number | undefined): void {
    if (!id) return;
    if (confirm('¿Eliminar esta ruta?')) {
      this.rutaService.eliminar(id).subscribe(() => this.cargarRutas());
    }
  }
}