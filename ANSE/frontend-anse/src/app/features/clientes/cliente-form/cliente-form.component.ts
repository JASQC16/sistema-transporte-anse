import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ClienteService } from '../../../core/services/cliente.service';
import { Cliente } from '../../../models/cliente';

@Component({
  selector: 'app-cliente-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './cliente-form.component.html',
  styleUrl: './cliente-form.component.css'
})
export class ClienteFormComponent implements OnInit {
  clienteForm: FormGroup;
  isEdit = false;
  clienteId?: number;

  constructor(
    private fb: FormBuilder,
    private clienteService: ClienteService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.clienteForm = this.fb.group({
      razonSocial: ['', [Validators.required, Validators.maxLength(150)]],
      rucDni: ['', [Validators.required, Validators.maxLength(20)]],
      telefono: ['', [Validators.maxLength(20)]],
      correo: ['', [Validators.maxLength(100), Validators.email]],
      direccion: ['', [Validators.required, Validators.maxLength(200)]]
    });
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.clienteId = +id;
      this.clienteService.obtener(this.clienteId).subscribe(cliente => {
        this.clienteForm.patchValue(cliente);
      });
    }
  }

  onSubmit(): void {
    if (this.clienteForm.invalid) {
      alert('Por favor, complete todos los campos requeridos.');
      return;
    }

    const cliente: Cliente = this.clienteForm.value;

    if (this.isEdit && this.clienteId) {
      this.clienteService.actualizar(this.clienteId, cliente).subscribe({
        next: () => this.router.navigate(['/clientes']),
        error: (err) => {
          const errorMsg = err.error?.message || 'Error al actualizar el cliente.';
          alert(errorMsg);
        }
      });
    } else {
      this.clienteService.crear(cliente).subscribe({
        next: () => this.router.navigate(['/clientes']),
        error: (err) => {
          const errorMsg = err.error?.message || 'Error al crear el cliente.';
          alert(errorMsg);
        }
      });
    }
  }

  cancelar(): void {
    this.router.navigate(['/clientes']);
  }
}