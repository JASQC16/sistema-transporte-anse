import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoriaService } from '../../../core/services/categoria.service';
import { Categoria } from '../../../models/categoria';

@Component({
  selector: 'app-categoria-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './categoria-form.component.html',
  styleUrl: './categoria-form.component.css'
})
export class CategoriaFormComponent implements OnInit {
  categoriaForm: FormGroup;
  isEdit = false;
  categoriaId?: number;

  constructor(
    private fb: FormBuilder,
    private categoriaService: CategoriaService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.categoriaForm = this.fb.group({
      nombre: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
      descripcion: ['', [Validators.maxLength(500)]]
    });
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.categoriaId = +id;
      this.categoriaService.obtener(this.categoriaId).subscribe(categoria => {
        this.categoriaForm.patchValue(categoria);
      });
    }
  }

  onSubmit(): void {
    if (this.categoriaForm.invalid) {
      alert('Por favor, complete todos los campos requeridos.');
      return;
    }

    const categoria: Categoria = this.categoriaForm.value;

    if (this.isEdit && this.categoriaId) {
      this.categoriaService.actualizar(this.categoriaId, categoria).subscribe({
        next: () => this.router.navigate(['/categorias']),
        error: (err) => {
          const errorMsg = err.error?.message || 'Error al actualizar la categoría.';
          alert(errorMsg);
        }
      });
    } else {
      this.categoriaService.crear(categoria).subscribe({
        next: () => this.router.navigate(['/categorias']),
        error: (err) => {
          const errorMsg = err.error?.message || 'Error al crear la categoría.';
          alert(errorMsg);
        }
      });
    }
  }

  cancelar(): void {
    this.router.navigate(['/categorias']);
  }
}