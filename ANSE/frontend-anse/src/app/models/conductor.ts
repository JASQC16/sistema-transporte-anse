export interface Conductor {
    id?: number;
    nombres: string;
    apellidos: string;
    licencia: string;
    telefono: string;
    categoriaLicencia: string;
    estado?: boolean;
  }