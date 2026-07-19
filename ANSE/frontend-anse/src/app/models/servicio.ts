export interface Servicio {
    id?: number;
    clienteId: number;
    categoriaId: number;
    rutaId: number;
    conductorId: number;
    vehiculoId: number;
    fechaServicio: string;
    horaInicio?: string;
    horaFin?: string;
    estado: string;
    costo: number;
    observaciones?: string;
  }