import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { MantenimientoService } from '../../../core/services/mantenimiento.service';
import { AuthService } from '../../../core/services/auth.service';
import { Mantenimiento } from '../../../models/mantenimiento';

@Component({
  selector: 'app-mantenimiento-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './mantenimiento-list.component.html',
  styleUrl: './mantenimiento-list.component.css'
})
export class MantenimientoListComponent implements OnInit {
  mantenimientos: Mantenimiento[] = [];
  isAdmin = false;

  constructor(
    private mantenimientoService: MantenimientoService,
    private authService: AuthService
  ) {
    this.isAdmin = this.authService.isAdmin();
  }

  ngOnInit(): void {
    this.cargarMantenimientos();
  }

  cargarMantenimientos(): void {
    this.mantenimientoService.listar().subscribe(data => this.mantenimientos = data);
  }

  eliminar(id: number | undefined): void {
    if (!id) return;
    if (confirm('¿Eliminar este mantenimiento?')) {
      this.mantenimientoService.eliminar(id).subscribe(() => this.cargarMantenimientos());
    }
  }
}