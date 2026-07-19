import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { ClienteService } from '../../../core/services/cliente.service';
import { AuthService } from '../../../core/services/auth.service';
import { Cliente } from '../../../models/cliente';

@Component({
  selector: 'app-cliente-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './cliente-list.component.html',
  styleUrl: './cliente-list.component.css'
})
export class ClienteListComponent implements OnInit {
  clientes: Cliente[] = [];
  isAdmin = false;

  constructor(
    private clienteService: ClienteService,
    private authService: AuthService
  ) {
    this.isAdmin = this.authService.isAdmin();
  }

  ngOnInit(): void {
    this.cargarClientes();
  }

  cargarClientes(): void {
    this.clienteService.listar().subscribe(data => this.clientes = data);
  }

  eliminar(id: number | undefined): void {
    if (!id) return;
    if (confirm('¿Eliminar este cliente?')) {
      this.clienteService.eliminar(id).subscribe(() => this.cargarClientes());
    }
  }
}