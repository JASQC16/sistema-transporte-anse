import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MantenimientoService } from '../../../core/services/mantenimiento.service';
import { Mantenimiento } from '../../../models/mantenimiento';

@Component({
  selector: 'app-mantenimiento-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './mantenimiento-form.component.html',
  styleUrl: './mantenimiento-form.component.css'
})
export class MantenimientoFormComponent implements OnInit {
  mantenimientoForm: FormGroup;
  isEdit = false;
  mantenimientoId?: number;

  constructor(
    private fb: FormBuilder,
    private mantenimientoService: MantenimientoService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.mantenimientoForm = this.fb.group({
      vehiculoId: ['', Validators.required],
      fecha: ['', Validators.required],
      tipo: ['', [Validators.required, Validators.maxLength(100)]],
      descripcion: ['', [Validators.required]],
      costo: ['', [Validators.required, Validators.min(0.01)]],
      kilometraje: ['', [Validators.required, Validators.min(0)]]
    });
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.mantenimientoId = +id;
      this.mantenimientoService.obtener(this.mantenimientoId).subscribe(mantenimiento => {
        this.mantenimientoForm.patchValue(mantenimiento);
      });
    }
  }

  onSubmit(): void {
    if (this.mantenimientoForm.invalid) {
      alert('Por favor, complete todos los campos requeridos.');
      return;
    }

    const mantenimiento: Mantenimiento = this.mantenimientoForm.value;

    if (this.isEdit && this.mantenimientoId) {
      this.mantenimientoService.actualizar(this.mantenimientoId, mantenimiento).subscribe({
        next: () => this.router.navigate(['/mantenimientos']),
        error: (err) => {
          const errorMsg = err.error?.message || 'Error al actualizar el mantenimiento.';
          alert(errorMsg);
        }
      });
    } else {
      this.mantenimientoService.crear(mantenimiento).subscribe({
        next: () => this.router.navigate(['/mantenimientos']),
        error: (err) => {
          const errorMsg = err.error?.message || 'Error al crear el mantenimiento.';
          alert(errorMsg);
        }
      });
    }
  }

  cancelar(): void {
    this.router.navigate(['/mantenimientos']);
  }
}