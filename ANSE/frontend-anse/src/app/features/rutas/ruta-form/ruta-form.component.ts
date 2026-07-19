import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { RutaService } from '../../../core/services/ruta.service';
import { Ruta } from '../../../models/ruta';

@Component({
  selector: 'app-ruta-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './ruta-form.component.html',
  styleUrl: './ruta-form.component.css'
})
export class RutaFormComponent implements OnInit {
  rutaForm: FormGroup;
  isEdit = false;
  rutaId?: number;

  constructor(
    private fb: FormBuilder,
    private rutaService: RutaService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.rutaForm = this.fb.group({
      origen: ['', [Validators.required, Validators.maxLength(150)]],
      destino: ['', [Validators.required, Validators.maxLength(150)]],
      distanciaKm: ['', [Validators.required, Validators.min(0.1)]],
      tiempoEstimado: ['', [Validators.required, Validators.maxLength(30)]]
    });
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.rutaId = +id;
      this.rutaService.obtener(this.rutaId).subscribe(ruta => {
        this.rutaForm.patchValue(ruta);
      });
    }
  }

  onSubmit(): void {
    if (this.rutaForm.invalid) {
      alert('Por favor, complete todos los campos requeridos.');
      return;
    }

    const ruta: Ruta = this.rutaForm.value;

    if (this.isEdit && this.rutaId) {
      this.rutaService.actualizar(this.rutaId, ruta).subscribe({
        next: () => this.router.navigate(['/rutas']),
        error: (err) => {
          const errorMsg = err.error?.message || 'Error al actualizar la ruta.';
          alert(errorMsg);
        }
      });
    } else {
      this.rutaService.crear(ruta).subscribe({
        next: () => this.router.navigate(['/rutas']),
        error: (err) => {
          const errorMsg = err.error?.message || 'Error al crear la ruta.';
          alert(errorMsg);
        }
      });
    }
  }

  cancelar(): void {
    this.router.navigate(['/rutas']);
  }
}