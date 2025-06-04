import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ReporteClientesComponent } from '../reporte-clientes/reporte-clientes.component';
import { Ticket } from '../generar-ticket/generar-ticket.component';
import { TicketAModificarService } from '../ticket-amodificar.service';

@Component({
  selector: 'app-detalles',
  imports: [],
  templateUrl: './detalles.component.html',
  styleUrl: './detalles.component.css'
})
export class DetallesComponent implements OnInit{
  @ViewChild(ReporteClientesComponent) reporteClientes?: ReporteClientesComponent;
  ticketactual?:Ticket;

  constructor(private router: Router, private routerActive: ActivatedRoute, private ticketService:TicketAModificarService) {}


  ngOnInit(): void {
    const ticket = localStorage.getItem("ticketseleccionado");
    if(ticket){
      this.ticketactual = JSON.parse(ticket);
    }
    localStorage.setItem("ticketseleccionado","");
    this.ticketService.ticket$.subscribe(obj =>{
      this.ticketactual = obj;
    })
  }

  regresar(){
    this.router.navigate(['reporte-clientes']);
  }

}
