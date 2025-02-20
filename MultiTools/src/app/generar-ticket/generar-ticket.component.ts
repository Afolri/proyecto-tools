import { CommonModule } from '@angular/common';
import { Component,EventEmitter,Input, OnInit, Output} from '@angular/core';
import { FormsModule } from '@angular/forms';

export interface Tickets{
  numero_ticket:number;
  asunto:String;
  descripcion:string;
  estado:string;
  cerrado:boolean;
} 

export interface Clientes{
  numero_cliente:number;
  nombre_cliente:string;
  correo:string;
  telefono:string;
}
export interface ProductoTicket{
  numero_producto:number;
  numero_serie_modelo:string;
  numero_compra_cot:string;
}

@Component({
  selector: 'app-generar-ticket',
  imports: [CommonModule, FormsModule],
  templateUrl: './generar-ticket.component.html',
  styleUrl: './generar-ticket.component.css'
})
export class GenerarTicketComponent implements OnInit  {
  @Output() mostrarChange = new EventEmitter<boolean>();
  @Output() obtenerDatos = new EventEmitter<void>();
  
  @Input() ticket:any;
  @Input() modo: 'crear'|'editar' ='crear';
  ticketGuardar:Tickets ={
    numero_ticket:0,
    asunto:'',
    descripcion:'',
    estado:'Nuevo',
    cerrado:false
  };
  clienteGuardar:Clientes ={
    numero_cliente:0,
    nombre_cliente:'',
    correo:'',
    telefono:''
  };
  productoGuardar:ProductoTicket ={
    numero_producto:0,
    numero_serie_modelo:'',
    numero_compra_cot:''

  };

  ngOnInit() {
    if (this.modo === 'editar' && this.ticket) {
      this.productoGuardar.numero_serie_modelo = this.ticket.numero_serie_modelo;
      this.productoGuardar.numero_compra_cot = this.ticket.numero_compra_cot;
      this.clienteGuardar.nombre_cliente = this.ticket.nombre_cliente;
      this.clienteGuardar.correo = this.ticket.correo;
      this.clienteGuardar.telefono = this.ticket.telefono;
      this.ticketGuardar.asunto = this.ticket.asunto;
      this.ticketGuardar.descripcion = this.ticket.descripcion;

    }
  }
  cancelarFormulario(){
    this.mostrarChange.emit(false);
  }
  editarTicket(){
    this.obtenerDatos.emit();
  }

  
  crearticket(event: Event){
    event.preventDefault();


    let numero_serie_modelo = this.productoGuardar.numero_serie_modelo;
    let numero_compra_cot= this.productoGuardar.numero_compra_cot;
    let nombre_cliente = this.clienteGuardar.nombre_cliente;
    let correo = this.clienteGuardar.correo;
    let telefono = this.clienteGuardar.telefono;
    let asunto = this.ticketGuardar.asunto;
    let descripcion = this.ticketGuardar.descripcion;



    fetch("http://localhost:8080/admin/reporte-tickets/crear-ticket", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        numero_serie_modelo,
        numero_compra_cot,
        nombre_cliente,
        correo,
        telefono,
        asunto,
        descripcion
      })
    })
      .then(response => {
        if (!response.ok) {
          return response.json().then(error =>{
            
            if(error.error != ""){
              const errorMessage = error.error;
              console.log(errorMessage)
            }
          })
        }
        return response.text().then(text => {
          // Si hay texto, lo parseamos a JSON, de lo contrario retornamos un objeto vacío
          return text ? JSON.parse(text) : {};
        });
      })
      .catch(error => console.error("Error:", error));
  }

  actualizarTicket(){
    fetch(`http://localhost:8080/admin/reporte-tickets/actualizar-ticket`,{
      method: 'PUT',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({
        ticket: {
          numero_ticket: this.ticket.numero_ticket,
          asunto: this.ticketGuardar.asunto,
          descripcion: this.ticketGuardar.descripcion
      },
      productoticket: {
          numero_producto: this.ticket.numero_producto,
          numero_serie_modelo: this.productoGuardar.numero_serie_modelo,
          numero_compra_cot: this.productoGuardar.numero_compra_cot
      },
      cliente: {
          numero_cliente: this.ticket.numero_cliente,
          nombre_cliente: this.clienteGuardar.nombre_cliente,
          correo: this.clienteGuardar.correo,
          telefono: this.clienteGuardar.telefono
      }
      }),
    })
   .then(response => {
     if(response.ok){
       this.mostrarChange.emit(false);
       this.editarTicket();
       console.log('Ticket actualizado correctamente');
     }else{
       console.error('Error al actualizar el ticket');
     }
   })
   .catch(error => console.error("Error", error));
  }

  camposInvalidos(form: any): boolean {
    return form && form.controls &&
      Object.keys(form.controls).some(
        campo => form.controls[campo].invalid && form.controls[campo].touched
      );
  }
  contieneNumeros(texto: string): boolean {
    return /\d/.test(texto); // Devuelve true si encuentra un número
  }




}
