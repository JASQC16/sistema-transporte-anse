import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ServicioService } from '../../../core/services/servicio.service';
import { Servicio } from '../../../models/servicio';

@Component({
  selector: 'app-servicio-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './servicio-form.component.html',
  styleUrl: './servicio-form.component.css'
})
export class ServicioFormComponent implements OnInit {
  servicioForm: FormGroup;
  isEdit = false;
  servicioId?: number;

  constructor(
    private fb: FormBuilder,
    private servicioService: ServicioService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.servicioForm = this.fb.group({
      clienteId: ['', Validators.required],
      categoriaId: ['', Validators.required],
      rutaId: ['', Validators.required],
      conductorId: ['', Validators.required],
      vehiculoId: ['', Validators.required],
      fechaServicio: ['', Validators.required],
      horaInicio: [''],
      horaFin: [''],
      estado: ['', Validators.required],
      costo: ['', [Validators.required, Validators.min(0.01)]],
      observaciones: ['']
    });
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.servicioId = +id;
      this.servicioService.obtener(this.servicioId).subscribe(servicio => {
        this.servicioForm.patchValue(servicio);
      });
    }
  }

  onSubmit(): void {
    if (this.servicioForm.invalid) {
      alert('Por favor, complete todos los campos requeridos.');
      return;
    }

    const servicio: Servicio = this.servicioForm.value;

    if (this.isEdit && this.servicioId) {
      this.servicioService.actualizar(this.servicioId, servicio).subscribe({
        next: () => this.router.navigate(['/servicios']),
        error: (err) => {
          const errorMsg = err.error?.message || 'Error al actualizar el servicio.';
          alert(errorMsg);
        }
      });
    } else {
      this.servicioService.crear(servicio).subscribe({
        next: () => this.router.navigate(['/servicios']),
        error: (err) => {
          const errorMsg = err.error?.message || 'Error al crear el servicio.';
          alert(errorMsg);
        }
      });
    }
  }

  cancelar(): void {
    this.router.navigate(['/servicios']);
  }
}