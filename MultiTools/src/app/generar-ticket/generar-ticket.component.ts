import { CommonModule } from '@angular/common';
import { Component,OnInit} from '@angular/core';
import { FormsModule } from '@angular/forms';

interface Tickets{
  numero_ticket:number;
  asunto:String;
  descripcion:string;
  estado:string;
  cerrado:boolean;
} 
interface Clientes{
  numero_cliente:number;
  nombre_cliente:string;
  correo:string;
  telefono:string;
}
interface ProductoTicket{
  numero_producto_modelo:number;
  numero_serie_producto:string;
  numero_compra_cot:string;
}

@Component({
  selector: 'app-generar-ticket',
  imports: [CommonModule, FormsModule],
  templateUrl: './generar-ticket.component.html',
  styleUrl: './generar-ticket.component.css'
})
export class GenerarTicketComponent implements OnInit  {
  ticketGuardar:Tickets ={
    numero_ticket:0,
    asunto:'',
    descripcion:'',
    estado:'Nuevo',
    cerrado:false
  }
  clienteGuardar:Clientes ={
    numero_cliente:0,
    nombre_cliente:'',
    correo:'',
    telefono:''
  };
  productoGuardar:ProductoTicket ={
    numero_producto_modelo:0,
    numero_serie_producto:'',
    numero_compra_cot:''

  };
  ngOnInit() {
  }
  crearticket(event: Event){
    event.preventDefault();


    let numeroDeSerie = this.productoGuardar.numero_serie_producto;
    let numeroCompraCot= this.productoGuardar.numero_compra_cot;
    let nombreCliente = this.clienteGuardar.nombre_cliente;
    let correo = this.clienteGuardar.correo;
    let telefono = this.clienteGuardar.telefono;
    let asunto = this.ticketGuardar.asunto;
    let descripcion = this.ticketGuardar.descripcion;



    fetch("http://localhost:8080/admin/reporte-tickets/crear-ticket", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        numeroDeSerie,
        numeroCompraCot,
        nombreCliente,
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
              alert(`Ocurrio un error: ${errorMessage}`)
            }
          })
        }
        alert("guardado con éxito")
        return response.text().then(text => {
          // Si hay texto, lo parseamos a JSON, de lo contrario retornamos un objeto vacío
          return text ? JSON.parse(text) : {};
        });
      })
      .catch(error => console.error("Error:", error));
  }

  camposInvalidos(form: any): boolean {
    return form && form.controls &&
      Object.keys(form.controls).some(
        campo => form.controls[campo].invalid && form.controls[campo].touched
      );
  }
}
