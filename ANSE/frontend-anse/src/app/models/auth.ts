export interface LoginRequest {
    usuario: string;
    contrasenia: string;
  }
  
  export interface RegisterRequest {
    usuario: string;
    contrasenia: string;
    rol?: string;
  }
  
  export interface AuthResponse {
    token: string;
    type: string;
    usuario: string;
    rol: string;
  }