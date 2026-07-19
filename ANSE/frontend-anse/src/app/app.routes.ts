import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth-guard';

export const routes: Routes = [
  {
    path: 'login',
    loadComponent: () => import('./features/auth/login/login.component').then(m => m.LoginComponent)
  },
  {
    path: 'register',
    loadComponent: () => import('./features/auth/register/register.component').then(m => m.RegisterComponent)
  },
  {
    path: 'categorias',
    loadComponent: () => import('./features/categorias/categoria-list/categoria-list.component').then(m => m.CategoriaListComponent)
  },
  {
    path: 'categorias/nuevo',
    loadComponent: () => import('./features/categorias/categoria-form/categoria-form.component').then(m => m.CategoriaFormComponent),
    canActivate: [authGuard]
  },
  {
    path: 'categorias/editar/:id',
    loadComponent: () => import('./features/categorias/categoria-form/categoria-form.component').then(m => m.CategoriaFormComponent),
    canActivate: [authGuard]
  },
  {
    path: 'clientes',
    loadComponent: () => import('./features/clientes/cliente-list/cliente-list.component').then(m => m.ClienteListComponent)
  },
  {
    path: 'clientes/nuevo',
    loadComponent: () => import('./features/clientes/cliente-form/cliente-form.component').then(m => m.ClienteFormComponent),
    canActivate: [authGuard]
  },
  {
    path: 'clientes/editar/:id',
    loadComponent: () => import('./features/clientes/cliente-form/cliente-form.component').then(m => m.ClienteFormComponent),
    canActivate: [authGuard]
  },
  {
    path: 'conductores',
    loadComponent: () => import('./features/conductores/conductor-list/conductor-list.component').then(m => m.ConductorListComponent)
  },
  {
    path: 'conductores/nuevo',
    loadComponent: () => import('./features/conductores/conductor-form/conductor-form.component').then(m => m.ConductorFormComponent),
    canActivate: [authGuard]
  },
  {
    path: 'conductores/editar/:id',
    loadComponent: () => import('./features/conductores/conductor-form/conductor-form.component').then(m => m.ConductorFormComponent),
    canActivate: [authGuard]
  },
  {
    path: 'vehiculos',
    loadComponent: () => import('./features/vehiculos/vehiculo-list/vehiculo-list.component').then(m => m.VehiculoListComponent)
  },
  {
    path: 'vehiculos/nuevo',
    loadComponent: () => import('./features/vehiculos/vehiculo-form/vehiculo-form.component').then(m => m.VehiculoFormComponent),
    canActivate: [authGuard]
  },
  {
    path: 'vehiculos/editar/:id',
    loadComponent: () => import('./features/vehiculos/vehiculo-form/vehiculo-form.component').then(m => m.VehiculoFormComponent),
    canActivate: [authGuard]
  },
  {
    path: 'rutas',
    loadComponent: () => import('./features/rutas/ruta-list/ruta-list.component').then(m => m.RutaListComponent)
  },
  {
    path: 'rutas/nuevo',
    loadComponent: () => import('./features/rutas/ruta-form/ruta-form.component').then(m => m.RutaFormComponent),
    canActivate: [authGuard]
  },
  {
    path: 'rutas/editar/:id',
    loadComponent: () => import('./features/rutas/ruta-form/ruta-form.component').then(m => m.RutaFormComponent),
    canActivate: [authGuard]
  },
  {
    path: 'servicios',
    loadComponent: () => import('./features/servicios/servicio-list/servicio-list.component').then(m => m.ServicioListComponent)
  },
  {
    path: 'servicios/nuevo',
    loadComponent: () => import('./features/servicios/servicio-form/servicio-form.component').then(m => m.ServicioFormComponent),
    canActivate: [authGuard]
  },
  {
    path: 'servicios/editar/:id',
    loadComponent: () => import('./features/servicios/servicio-form/servicio-form.component').then(m => m.ServicioFormComponent),
    canActivate: [authGuard]
  },
  {
    path: 'boletas',
    loadComponent: () => import('./features/boletas/boleta-list/boleta-list.component').then(m => m.BoletaListComponent)
  },
  {
    path: 'boletas/nuevo',
    loadComponent: () => import('./features/boletas/boleta-form/boleta-form.component').then(m => m.BoletaFormComponent),
    canActivate: [authGuard]
  },
  {
    path: 'mantenimientos',
    loadComponent: () => import('./features/mantenimientos/mantenimiento-list/mantenimiento-list.component').then(m => m.MantenimientoListComponent)
  },
  {
    path: 'mantenimientos/nuevo',
    loadComponent: () => import('./features/mantenimientos/mantenimiento-form/mantenimiento-form.component').then(m => m.MantenimientoFormComponent),
    canActivate: [authGuard]
  },
  { path: '', redirectTo: '/categorias', pathMatch: 'full' },
  { path: '**', redirectTo: '/categorias' }
];