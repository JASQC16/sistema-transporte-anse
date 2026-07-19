import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { VehiculoService } from '../../../core/services/vehiculo.service';
import { AuthService } from '../../../core/services/auth.service';
import { Vehiculo } from '../../../models/vehiculo';

@Component({
  selector: 'app-vehiculo-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './vehiculo-list.component.html',
  styleUrl: './vehiculo-list.component.css'
})
export class VehiculoListComponent implements OnInit {
  vehiculos: Vehiculo[] = [];
  isAdmin = false;

  constructor(
    private vehiculoService: VehiculoService,
    private authService: AuthService
  ) {
    this.isAdmin = this.authService.isAdmin();
  }

  ngOnInit(): void {
    this.cargarVehiculos();
  }

  cargarVehiculos(): void {
    this.vehiculoService.listar().subscribe(data => this.vehiculos = data);
  }

  eliminar(id: number | undefined): void {
    if (!id) return;
    if (confirm('¿Eliminar este vehículo?')) {
      this.vehiculoService.eliminar(id).subscribe(() => this.cargarVehiculos());
    }
  }
}