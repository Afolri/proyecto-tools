import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterOutlet } from '@angular/router';


interface TablaTickets{
  numero_ticket:number;
  asunto:String;
  descripcion:string;
  estado:string;
  cerrado:boolean;
  numero_agente:number;

  numero_cliente:number;
  nombre_cliente:string;
  correo:string;
  telefono:string;

  numero_producto_modelo:number;
  numero_serie_producto:string;
  numero_compra_cot:string;
}

@Component({
  selector: 'app-reporte-clientes',
  imports: [CommonModule, FormsModule,RouterOutlet],
  templateUrl: './reporte-clientes.component.html',
  styleUrl: './reporte-clientes.component.css'
})
export class ReporteClientesComponent implements OnInit {
  tablatickets:TablaTickets [] = [];

  ngOnInit(): void {
    this.cargarProductos();
  }

  cargarProductos(){
    fetch('http://localhost:8080/admin/reporte-tickets/buscar-tickets')
    .then(respuesta => respuesta.json())
    .then((productos:TablaTickets[])=>{
      console.log('Productos recibidos:',productos);
      this.tablatickets = productos;
    });
  }
}
