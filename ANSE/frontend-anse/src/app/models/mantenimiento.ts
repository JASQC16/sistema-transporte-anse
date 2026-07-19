export interface Mantenimiento {
    id?: number;
    vehiculoId: number;
    fecha: string;
    tipo: string;
    descripcion: string;
    costo: number;
    kilometraje: number;
  }