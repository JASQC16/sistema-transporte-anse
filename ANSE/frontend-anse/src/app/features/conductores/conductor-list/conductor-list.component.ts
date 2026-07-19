import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { ConductorService } from '../../../core/services/conductor.service';
import { AuthService } from '../../../core/services/auth.service';
import { Conductor } from '../../../models/conductor';

@Component({
  selector: 'app-conductor-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './conductor-list.component.html',
  styleUrl: './conductor-list.component.css'
})
export class ConductorListComponent implements OnInit {
  conductores: Conductor[] = [];
  isAdmin = false;

  constructor(
    private conductorService: ConductorService,
    private authService: AuthService
  ) {
    this.isAdmin = this.authService.isAdmin();
  }

  ngOnInit(): void {
    this.cargarConductores();
  }

  cargarConductores(): void {
    this.conductorService.listar().subscribe(data => this.conductores = data);
  }

  eliminar(id: number | undefined): void {
    if (!id) return;
    if (confirm('¿Eliminar este conductor?')) {
      this.conductorService.eliminar(id).subscribe(() => this.cargarConductores());
    }
  }
}