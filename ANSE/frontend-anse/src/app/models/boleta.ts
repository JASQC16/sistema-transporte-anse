export interface Boleta {
    id?: number;
    servicioId: number;
    numero: string;
    fechaEmision: string;
    subtotal: number;
    igv: number;
    total: number;
    pdf?: string;
  }