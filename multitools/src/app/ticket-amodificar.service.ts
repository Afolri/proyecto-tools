import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Ticket } from './generar-ticket/generar-ticket.component';

@Injectable({
  providedIn: 'root'
})
export class TicketAModificarService {

  private ticketModificado = new BehaviorSubject<Ticket>({
    numero_ticket:0,
    numero_producto: 0,
    tipo_codigo:"",
    numero_compra_cot: "",
    asunto:"",
    numero_cliente:0,
    nombre_cliente: "",
    telefono:"",
    correo: "",
    descripcion: "",
    estado: "",
    cerrado: false,
    seleccionado: false,
    opciones:false,
    numero_agente:0,
    fecha: new Date,
    hora: new Date
  });
  ticket$ = this.ticketModificado.asObservable();
  constructor() { }

  emitirTicket(ticket:Ticket){
    this.ticketModificado.next(ticket);
  }
}
