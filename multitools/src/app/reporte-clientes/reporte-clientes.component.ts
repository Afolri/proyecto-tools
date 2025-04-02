import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { FaIconLibrary, FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { GenerarTicketComponent, } from "../generar-ticket/generar-ticket.component";
import { MensajeAvisoComponent } from "../mensaje-aviso/mensaje-aviso.component";
import { NotificacionesComponent } from "../notificaciones/notificaciones.component";
import{Ticket} from "../generar-ticket/generar-ticket.component";
import { EditarTicketComponent } from "../editar-ticket/editar-ticket.component";
import { LoginComponent, Usuario } from '../login/login.component';
import { faBars, faComment, faLock } from '@fortawesome/free-solid-svg-icons';
import { AuthService } from '../services/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from '../../environments/environment';
import { tick } from '@angular/core/testing';



const baseURL = `${environment.URL_BASE}`;

@Component({
  selector: 'app-reporte-clientes',
  imports: [
    CommonModule,
    FormsModule,
    MensajeAvisoComponent,
    FontAwesomeModule,
    NotificacionesComponent,
    EditarTicketComponent],
  templateUrl: './reporte-clientes.component.html',
  styleUrls: ['./reporte-clientes.component.css']
})

export class ReporteClientesComponent implements OnInit {
  /**Aquí se guardara el ticket actual */
  ticketActual!:Ticket;
  /**Aquí se guarda el usuario que actualmente esta loggeado */
  usuarioActual!:Usuario;
  /**creando las variables para exportar de los iconos */
  faComment = faComment;
  faLock = faLock;
  faBars = faBars;
  focus = false;

  /**exportación de los detalles de ticket actual */
  ticketSeleccionadoEntidad?:Ticket;
  /**Variable para saber si esta activado el estilo para el icono lock */
  lockIcono:boolean = false;
  /**Variable para saber si esta activado el estilo para el icono edit */
  editIcono:boolean = false;

  /**Sirve para saber si hay notificaciones pendientes */
  @ViewChild(NotificacionesComponent) notificacionComp!:NotificacionesComponent;
  @ViewChild(LoginComponent) loginComp!:LoginComponent;
  @ViewChild(GenerarTicketComponent) generarTicket!:GenerarTicketComponent;
  /**vairable para mostrar o no las notificaciones */
  mostrarNotificaciones:boolean = false;
  mensajeAviso:boolean = false;
  /**array donde se reciben todos los tickets*/
  tablatickets:Ticket [] = [];
  /**mostrar la interfaz de actualizar */
  mostrar:boolean = false;
  /**el modo del mensaje de aviso para confirmar una eliminación o una actualización */
  modoMensajeFormulario:'actualizar'|'eliminar'='actualizar';
  /**el ticket que es obtenido de un componente padre para poder ser pasado a otro componente */
  tablaTicketstemp: Ticket = {
    numero_ticket:0,
    numero_producto: 0,
    tipo_codigo:"",
    numero_compra_cot: "",
    asunto:"",
    numero_cliente:0,
    nombre_cliente: "",
    telefono:"",
    correo: "",
    descripcion: "",
    estado: "",
    cerrado: false,
    seleccionado: false,
    opciones:false,
    numero_agente:0,
    fecha: new Date,
    hora: new Date
  };


  constructor(library: FaIconLibrary, private authService: AuthService, private router: Router) {
    library.addIcons(faComment);
    library.addIcons(faLock);
    library.addIcons(faBars);
  }
  ngOnInit(): void {
    this.authService.usuarioActual$.subscribe(usuario => {
      if (usuario) { 
        this.usuarioActual = usuario;
        this.cargarTickets();
      }
    });
  }

  /**Activa el atributo de opciones cuando es verdadero aplica un estilo */
  activarCheck(ticketactual:Ticket){
    const ticketencontrado = this.tablatickets.find(ticket => ticket.numero_ticket == ticketactual.numero_ticket);
    if(ticketencontrado){
      ticketencontrado.opciones = !ticketencontrado.opciones;
    }
  }

  ocultar(){
    this.mostrar=false;
  }
  ocultarMensajeAviso(){
    this.mensajeAviso=false;
  }
  editar(ticketactual:Ticket){
    this.tablaTicketstemp= ticketactual;
    this.mostrar=true;
  }
  solicitareliminar( ticketactual:Ticket){
    this.tablaTicketstemp = ticketactual;
    const ticketencontrado = this.tablatickets.find(ticket => ticket.numero_ticket == ticketactual.numero_ticket);
    if(ticketencontrado){
      ticketencontrado.seleccionado = true;
    }
    this.mostrarAviso('eliminar');
  }
  mostrarAviso(modo:'actualizar'|'eliminar'){
    this.modoMensajeFormulario = modo;
    this.mensajeAviso = true;
  }
  aceptaractualizar(){
    this.generarTicket.actualizarTicket();
    this.mensajeAviso = false;
  }
  desmarcar(){
    const ticketencontrado = this.tablatickets.find(ticket => ticket.numero_ticket == this.tablaTicketstemp.numero_ticket);
    if(ticketencontrado){
      ticketencontrado.seleccionado=false;
    }
  }

  estilosMarcado() {
    return {
      background: 'hsla(0, 77.80%, 31.80%, 0.78)',
      color: 'white',
      transition: 'background-color 0.3s ease' 
    };
  }
  

  

  cargarTickets() {
    const token = localStorage.getItem("token");

    if (!token) {
        alert("No se encontró un token. Inicia sesión nuevamente.");
        return;
    }

    fetch(`${baseURL}/admin/reporte-tickets/buscar-tickets?numeroUsuario=${this.usuarioActual.numero_usuario}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`Error ${response.status}: No se pudieron obtener los tickets.`);
        }
        return response.json();
    })
    .then((tickets: Ticket[]) => {
        this.tablatickets = tickets;
    })
    .catch(error => {
        console.error("Error:", error.message);
    });
}

  cerrarTicket(numero_ticket: number){
    const t = localStorage.getItem("token");
    fetch(`${baseURL}/admin/reporte-tickets/cerrar-ticket?numeroTicket=${numero_ticket}`,{
      method: 'PUT',
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${t}`
      }
    })
    .then(response =>{
      if(response.ok){
        this.mensajeAviso = false;
        this.cargarTickets();
      }else{
        console.error('Error al cerrar el ticket');
      }
    } )
    .then(ticket => console.log("Ticket cerrado",ticket))
    .catch(error => console.error("Error", error));
  }
  
  abrirNotificaciones(){
    if(this.mostrarNotificaciones){
      this.mostrarNotificaciones = false;
    }else{
      this.mostrarNotificaciones = true;
    }
  }
  

  estilo(){
    return {
      "background-color":"red"
    };
  }
  estiloLock(){
  return {
      "pointer-events":"all",
      "visibility": "visible",
      
      "transform": "scale(1)  translate(-27px, 0px)"
    };
  }
  estiloEdit(){
    return {
    
    "pointer-events": "all",
    "visibility": "visible",
    "transform": "scale(1) translate(0px, 27px)"
    };
  }
  estiloEstado(ticketEstado:string){
    if(ticketEstado == "NUEVO"){
      return {
        "background-color":"green"
      };
    }else if(ticketEstado == "CERRADO"){
      return {
        "background-color":"red"
      }
    }
    else if(ticketEstado == "ABIERTO"){
      return{
        "background-color":"blue"
      }
    }
    return null;
  }

  obtenerTicketSeleccionado(ticketSeleccionado?: number): Ticket  {
  
    let ticket = this.tablatickets?.find(obj => obj.numero_ticket === ticketSeleccionado);
    if(ticket){
      return this.ticketSeleccionadoEntidad = ticket;
    }else{
      throw new Error ('Ticket no encontrado')
    }
  }
  

  abrirDetallesNotificacion(numero_ticket:number){
    console.log("se ejecuto el metodo padre");
    this.ticketActual = this.obtenerTicketSeleccionado(numero_ticket);
    const ticketJson = JSON.stringify(this.ticketActual);
    this.router.navigate(['detalles'], {queryParams: { ticket:ticketJson}});
  }
}
