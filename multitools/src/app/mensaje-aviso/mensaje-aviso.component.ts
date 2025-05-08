import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { GenerarTicketComponent } from '../generar-ticket/generar-ticket.component';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-mensaje-aviso',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './mensaje-aviso.component.html',
  styleUrl: './mensaje-aviso.component.css'
})
export class MensajeAvisoComponent implements OnInit{
  formulario!:FormGroup;
  idticket:number = 0;
  @Output() cerrarTicketEvent = new EventEmitter();
  @Output() cerrarEvent = new EventEmitter();
  @Output() actualizarEvent = new EventEmitter();
  /**El resaltado de la fila desaparece*/
  @Output() desmarcarEvent = new EventEmitter();
  @Input() modoMensajeFormulario: 'actualizar'|'eliminar'='actualizar';
  /**View child aquí se va a utilzar para poder rescatar la funcion de actualizar y poder confirmarlo en el otro componente */
 
  constructor(private formBuilder:FormBuilder){

  }
  ngOnInit(): void {
    this.formulario = this.formBuilder.group({
      motivo:["",[Validators.required],[]]
    });
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
}
