import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Ticket } from '../generar-ticket/generar-ticket.component';

@Component({
  selector: 'app-editar-ticket',
  imports: [],
  templateUrl: './editar-ticket.component.html',
  styleUrl: './editar-ticket.component.css'
})
export class EditarTicketComponent {
  @Input() ticket!: Ticket;
  @Output() salirFormulario = new EventEmitter<Ticket>();


  cancelar(){
    this.salirFormulario.emit();
  }

}
