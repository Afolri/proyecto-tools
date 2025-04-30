export interface UsuarioResponseDTO{
    numero_usuario:number,
    correo:string,
    nombre_usuario:string,
    rol:'ADMIN'|'AGENTE'
}