import { ComentarioTicket } from "./comentarioTicket"

export interface Comentario{
    contenido:string,
    numero_comentario:number
    comentarioTicket?:ComentarioTicket;
}