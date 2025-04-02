import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ReporteClientesComponent } from '../reporte-clientes/reporte-clientes.component';
import { Ticket } from '../generar-ticket/generar-ticket.component';

@Component({
  selector: 'app-detalles',
  imports: [],
  templateUrl: './detalles.component.html',
  styleUrl: './detalles.component.css'
})
export class DetallesComponent implements OnInit{
  @ViewChild(ReporteClientesComponent) reporteClientes?: ReporteClientesComponent;
  ticketactual?:Ticket;

  constructor(private router: Router, private routerActive: ActivatedRoute) {}


  ngOnInit(): void {
    const ticketString = this.routerActive.snapshot.queryParamMap.get('ticket');
    if(ticketString){
      this.ticketactual = JSON.parse(ticketString);
    }

    
  }

  regresar(){
    this.router.navigate(['reporte-clientes']);
  }

}
