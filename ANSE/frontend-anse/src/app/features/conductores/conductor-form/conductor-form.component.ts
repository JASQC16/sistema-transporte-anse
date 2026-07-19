import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ConductorService } from '../../../core/services/conductor.service';
import { Conductor } from '../../../models/conductor';

@Component({
  selector: 'app-conductor-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './conductor-form.component.html',
  styleUrl: './conductor-form.component.css'
})
export class ConductorFormComponent implements OnInit {
  conductorForm: FormGroup;
  isEdit = false;
  conductorId?: number;

  constructor(
    private fb: FormBuilder,
    private conductorService: ConductorService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.conductorForm = this.fb.group({
      nombres: ['', [Validators.required, Validators.maxLength(100)]],
      apellidos: ['', [Validators.required, Validators.maxLength(100)]],
      licencia: ['', [Validators.required, Validators.maxLength(30)]],
      telefono: ['', [Validators.required, Validators.maxLength(20)]],
      categoriaLicencia: ['', [Validators.required, Validators.maxLength(20)]],
      estado: [true]
    });
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.conductorId = +id;
      this.conductorService.obtener(this.conductorId).subscribe(conductor => {
        this.conductorForm.patchValue(conductor);
      });
    }
  }

  onSubmit(): void {
    if (this.conductorForm.invalid) {
      alert('Por favor, complete todos los campos requeridos.');
      return;
    }

    const conductor: Conductor = this.conductorForm.value;

    if (this.isEdit && this.conductorId) {
      this.conductorService.actualizar(this.conductorId, conductor).subscribe({
        next: () => this.router.navigate(['/conductores']),
        error: (err) => {
          const errorMsg = err.error?.message || 'Error al actualizar el conductor.';
          alert(errorMsg);
        }
      });
    } else {
      this.conductorService.crear(conductor).subscribe({
        next: () => this.router.navigate(['/conductores']),
        error: (err) => {
          const errorMsg = err.error?.message || 'Error al crear el conductor.';
          alert(errorMsg);
        }
      });
    }
  }

  cancelar(): void {
    this.router.navigate(['/conductores']);
  }
}