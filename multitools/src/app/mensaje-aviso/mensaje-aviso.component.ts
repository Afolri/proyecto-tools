import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { GenerarTicketComponent, Ticket } from '../generar-ticket/generar-ticket.component';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { environment } from '../../environments/environment';
import { number } from 'zod';
import { TicketAModificarService } from '../ticket-amodificar.service';



@Component({
  selector: 'app-mensaje-aviso',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './mensaje-aviso.component.html',
  styleUrl: './mensaje-aviso.component.css'
})
export class MensajeAvisoComponent implements OnInit{
  baseUrl = environment.URL_BASE;
  formulario!:FormGroup;
  idticket:number = 0;
  @Output() cerrarTicketEvent = new EventEmitter();
  @Output() cerrarEvent = new EventEmitter();
  @Output() actualizarEvent = new EventEmitter();
  ticketSeleccionado!:Ticket;
  /**El resaltado de la fila desaparece*/
  @Output() desmarcarEvent = new EventEmitter();
  @Input() modoMensajeFormulario: 'actualizar'|'eliminar'='actualizar';
  /**View child aquí se va a utilzar para poder rescatar la funcion de actualizar y poder confirmarlo en el otro componente */
  constructor(private formBuilder:FormBuilder, private ticketModificar:TicketAModificarService){

  }
  ngOnInit(): void {
    this.formulario = this.formBuilder.group({
      motivo:["",[Validators.required],[]]
    });
    this.ticketModificar.ticket$.subscribe(obj =>{
      this.ticketSeleccionado = obj;
    })
    
  }

  aceptarcerrar(){
    this.cerrarTicketEvent.emit();
    this.desmarcarEvent.emit();
  }
  cancelar(){
    this.cerrarEvent.emit(false);
    this.desmarcarEvent.emit();

  }
  aceptaractualizar(){
    this.actualizarEvent.emit();
  }

    colocarPendiente(numeroTicket:number){
    fetch(`${this.baseUrl}/admin/reporte-tickets/ticket-pendiente?numeroTicket=${numeroTicket}`,{
      method:"PUT",
      headers:{
        "Content-Type":"application/json",
        "Authorization":`Bearer ${localStorage.getItem("token")}`
      }
    }).then(response =>{
      if(!response.ok){
        throw new Error ("Actualizacion causo un error");
      }
      this.ticketSeleccionado.estado = "PENDIENTE";
      this.ticketModificar.emitirTicket(this.ticketSeleccionado);
    })
  }

  botonPendiente(){
    let numero:number = 1;
    this.colocarPendiente(numero);
  }
}
