import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { BoletaService } from '../../../core/services/boleta.service';
import { AuthService } from '../../../core/services/auth.service';
import { Boleta } from '../../../models/boleta';

@Component({
  selector: 'app-boleta-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './boleta-list.component.html',
  styleUrl: './boleta-list.component.css'
})
export class BoletaListComponent implements OnInit {
  boletas: Boleta[] = [];
  isAdmin = false;

  constructor(
    private boletaService: BoletaService,
    private authService: AuthService
  ) {
    this.isAdmin = this.authService.isAdmin();
  }

  ngOnInit(): void {
    this.cargarBoletas();
  }

  cargarBoletas(): void {
    this.boletaService.listar().subscribe(data => this.boletas = data);
  }

  eliminar(id: number | undefined): void {
    if (!id) return;
    if (confirm('¿Eliminar esta boleta?')) {
      this.boletaService.eliminar(id).subscribe(() => this.cargarBoletas());
    }
  }
}