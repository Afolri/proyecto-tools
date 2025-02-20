import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterOutlet } from '@angular/router';
import { GenerarTicketComponent, } from "../generar-ticket/generar-ticket.component";


interface TablaTickets{
  numero_ticket:number;
  asunto:string;
  descripcion:string;
  estado:string;
  cerrado:boolean;
  numero_agente:number;

  numero_cliente:number;
  nombre_cliente:string;
  correo:string;
  telefono:string;

  numero_producto:number;
  numero_serie_modelo:string;
  numero_compra_cot:string;
  editar:boolean;
}

@Component({
  selector: 'app-reporte-clientes',
  imports: [CommonModule, FormsModule, GenerarTicketComponent],
  templateUrl: './reporte-clientes.component.html',
  styleUrls: ['./reporte-clientes.component.css']
})
export class ReporteClientesComponent implements OnInit {
  tablatickets:TablaTickets [] = [];
  mostrar:boolean = false;
  tickettemp:any;
  tablaTicketstemp:TablaTickets= { 
    numero_ticket:0,
    asunto:'',
    descripcion:'',
    estado:'',
    cerrado:false,
    numero_agente:0,
    numero_cliente:0,
    nombre_cliente:'',
    correo:'',
    telefono:'',
    numero_producto:0,
    numero_serie_modelo:'',
    numero_compra_cot:'',
    editar:false,
  };

  ngOnInit(): void {
    this.cargarTickets();
  }
  ocultar(){
    this.mostrar=false;
  }
  editar(ticketactual:TablaTickets){
    this.tickettemp= ticketactual;
    this.mostrar=true;
  }
  

  cargarTickets(){
    fetch('http://localhost:8080/admin/reporte-tickets/buscar-tickets')
    .then(respuesta => respuesta.json())
    .then((Tickets:TablaTickets[])=>{
      console.log('Tickets recibidos:',Tickets);
      this.tablatickets = Tickets;
    });
  }
  eliminar(numero_ticket: number){
    fetch(`http://localhost:8080/admin/reporte-tickets/eliminar-ticket?numeroTicket=${numero_ticket}`,{
      method: 'DELETE'
    })
    .then(response =>{
      if(response.ok){
        this.cargarTickets();
      }else{
        console.error('Error al eliminar el ticket');
      }
    } )
    .then(ticket => console.log("Ticke eliminado",ticket))
    .catch(error => console.error("Error", error));
  }
  

  

}
