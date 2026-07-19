import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { CategoriaService } from '../../../core/services/categoria.service';
import { AuthService } from '../../../core/services/auth.service';
import { Categoria } from '../../../models/categoria';

@Component({
  selector: 'app-categoria-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './categoria-list.component.html',
  styleUrl: './categoria-list.component.css'
})
export class CategoriaListComponent implements OnInit {
  categorias: Categoria[] = [];
  isAdmin = false;

  constructor(
    private categoriaService: CategoriaService,
    private authService: AuthService
  ) {
    this.isAdmin = this.authService.isAdmin();
  }

  ngOnInit(): void {
    this.cargarCategorias();
  }

  cargarCategorias(): void {
    this.categoriaService.listar().subscribe(data => this.categorias = data);
  }

  eliminar(id: number | undefined): void {
    if (!id) return;
    if (confirm('¿Eliminar esta categoría?')) {
      this.categoriaService.eliminar(id).subscribe(() => this.cargarCategorias());
    }
  }
}