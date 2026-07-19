import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BoletaService } from '../../../core/services/boleta.service';
import { Boleta } from '../../../models/boleta';

@Component({
  selector: 'app-boleta-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './boleta-form.component.html',
  styleUrl: './boleta-form.component.css'
})
export class BoletaFormComponent implements OnInit {
  boletaForm: FormGroup;
  isEdit = false;
  boletaId?: number;

  constructor(
    private fb: FormBuilder,
    private boletaService: BoletaService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.boletaForm = this.fb.group({
      servicioId: ['', Validators.required],
      numero: ['', [Validators.required, Validators.maxLength(20)]],
      fechaEmision: ['', Validators.required],
      subtotal: ['', [Validators.required, Validators.min(0.01)]],
      igv: ['', [Validators.required, Validators.min(0)]],
      total: ['', [Validators.required, Validators.min(0.01)]],
      pdf: ['']
    });
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.boletaId = +id;
      this.boletaService.obtener(this.boletaId).subscribe(boleta => {
        this.boletaForm.patchValue(boleta);
      });
    }
  }

  onSubmit(): void {
    if (this.boletaForm.invalid) {
      alert('Por favor, complete todos los campos requeridos.');
      return;
    }

    const boleta: Boleta = this.boletaForm.value;

    if (this.isEdit && this.boletaId) {
      this.boletaService.actualizar(this.boletaId, boleta).subscribe({
        next: () => this.router.navigate(['/boletas']),
        error: (err) => {
          const errorMsg = err.error?.message || 'Error al actualizar la boleta.';
          alert(errorMsg);
        }
      });
    } else {
      this.boletaService.crear(boleta).subscribe({
        next: () => this.router.navigate(['/boletas']),
        error: (err) => {
          const errorMsg = err.error?.message || 'Error al crear la boleta.';
          alert(errorMsg);
        }
      });
    }
  }

  cancelar(): void {
    this.router.navigate(['/boletas']);
  }
}