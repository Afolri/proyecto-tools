import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { GenerarTicketComponent } from '../generar-ticket/generar-ticket.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-mensaje-aviso',
  imports: [CommonModule],
  templateUrl: './mensaje-aviso.component.html',
  styleUrl: './mensaje-aviso.component.css'
})
export class MensajeAvisoComponent {

  idticket:number = 0;
  @Output() cerrarTicketEvent = new EventEmitter();
  @Output() cerrarEvent = new EventEmitter();
  @Output() actualizarEvent = new EventEmitter();
  /**El resaltado de la fila desaparece*/
  @Output() desmarcarEvent = new EventEmitter();
  @Input() modoMensajeFormulario: 'actualizar'|'eliminar'='actualizar';
  /**View child aquí se va a utilzar para poder rescatar la funcion de actualizar y poder confirmarlo en el otro componente */
 

  aceptarcerrar(){
    this.cerrarTicketEvent.emit();
  }
  cancelar(){
    this.cerrarEvent.emit(false);
    this.desmarcarEvent.emit();

  }
  aceptaractualizar(){
    this.actualizarEvent.emit();
  }
}
