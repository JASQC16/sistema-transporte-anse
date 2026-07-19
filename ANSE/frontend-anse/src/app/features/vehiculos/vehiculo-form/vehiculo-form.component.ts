import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { VehiculoService } from '../../../core/services/vehiculo.service';
import { Vehiculo } from '../../../models/vehiculo';

@Component({
  selector: 'app-vehiculo-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './vehiculo-form.component.html',
  styleUrl: './vehiculo-form.component.css'
})
export class VehiculoFormComponent implements OnInit {
  vehiculoForm: FormGroup;
  isEdit = false;
  vehiculoId?: number;

  constructor(
    private fb: FormBuilder,
    private vehiculoService: VehiculoService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.vehiculoForm = this.fb.group({
      placa: ['', [Validators.required, Validators.maxLength(10)]],
      marca: ['', [Validators.required, Validators.maxLength(50)]],
      modelo: ['', [Validators.required, Validators.maxLength(50)]],
      anio: ['', [Validators.required, Validators.min(1950), Validators.max(2099)]],
      capacidad: ['', [Validators.required, Validators.min(0.1)]],
      tipoCarga: ['', [Validators.required, Validators.maxLength(50)]],
      estado: ['', [Validators.required, Validators.maxLength(30)]]
    });
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.vehiculoId = +id;
      this.vehiculoService.obtener(this.vehiculoId).subscribe(vehiculo => {
        this.vehiculoForm.patchValue(vehiculo);
      });
    }
  }

  onSubmit(): void {
    if (this.vehiculoForm.invalid) {
      alert('Por favor, complete todos los campos requeridos.');
      return;
    }

    const vehiculo: Vehiculo = this.vehiculoForm.value;

    if (this.isEdit && this.vehiculoId) {
      this.vehiculoService.actualizar(this.vehiculoId, vehiculo).subscribe({
        next: () => this.router.navigate(['/vehiculos']),
        error: (err) => {
          const errorMsg = err.error?.message || 'Error al actualizar el vehículo.';
          alert(errorMsg);
        }
      });
    } else {
      this.vehiculoService.crear(vehiculo).subscribe({
        next: () => this.router.navigate(['/vehiculos']),
        error: (err) => {
          const errorMsg = err.error?.message || 'Error al crear el vehículo.';
          alert(errorMsg);
        }
      });
    }
  }

  cancelar(): void {
    this.router.navigate(['/vehiculos']);
  }
}