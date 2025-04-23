import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Ticket } from './generar-ticket/generar-ticket.component';

@Injectable({
  providedIn: 'root'
})
export class TicketServiceService {
  private ticketEmitido = new BehaviorSubject<Ticket[]>([]);
  tickets$ = this.ticketEmitido.asObservable();
  constructor() { }

  emitirTickets(ticket: Ticket[]){
    this.ticketEmitido.next(ticket);
  }
}
