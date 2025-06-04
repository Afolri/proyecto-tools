import { CommonModule } from '@angular/common';
import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FaIconLibrary, FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { GenerarTicketComponent, } from "../generar-ticket/generar-ticket.component";
import { MensajeAvisoComponent } from "../mensaje-aviso/mensaje-aviso.component";
import { NotificacionesComponent } from "../notificaciones/notificaciones.component";
import{Ticket} from "../generar-ticket/generar-ticket.component";
import { EditarTicketComponent } from "../editar-ticket/editar-ticket.component";
import { LoginComponent, Usuario } from '../login/login.component';
import { faBars, faComment, faHourglass, faLock } from '@fortawesome/free-solid-svg-icons';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment';
import { Client, IMessage } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { BehaviorSubject, filter, take, throwError } from 'rxjs';
import { WebSocketService } from '../web-socket.service';
import { TicketServiceService } from '../ticket-service.service';
import { NotificacionesResponse } from '../models/notificacionesResponse';
import { TicketAModificarService } from '../ticket-amodificar.service';
import { DetallesComponent } from "../detalles/detalles.component";


const baseURL = `${environment.URL_BASE}`;

@Component({
  selector: 'app-reporte-clientes',
  imports: [
    CommonModule,
    FormsModule,
    MensajeAvisoComponent,
    FontAwesomeModule,
    EditarTicketComponent,
    ReactiveFormsModule,
    DetallesComponent
],
  templateUrl: './reporte-clientes.component.html',
  styleUrls: ['./reporte-clientes.component.css']
})

export class ReporteClientesComponent implements OnInit {
  formulario!:FormGroup;
  refTicketComentarios!:number;
  /**Donde se guardaraon los tickets que se pasen por el socket */
  ticketsSocket:Ticket[] = [];
  /**Se pasaran las notificaciones que se obtengan de una unica consulta a la base de datos */
  notificaciones:NotificacionesResponse[] = [];
  /**Variable que guarda si hay notificaciones pendientes */
  notificacionesSinLeer:boolean = false;

  
  /**Aquí se guardara el ticket actual */
  ticketActual!:Ticket;
  /**Aquí se guarda el usuario que actualmente esta loggeado */
  usuarioActual!:Usuario;
  /**creando las variables para exportar los iconos */
  faComment = faComment;
  faLock = faLock;
  faBars = faBars;
  faHourgalss = faHourglass;
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
  public tablatickets:Ticket [] = [];
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
  @Output() eventTicket = new EventEmitter<any []>();


  constructor(library: FaIconLibrary, private authService: AuthService, private router: Router,
    private webSocketService:WebSocketService, private ticketService:TicketServiceService,
    private formBuilder:FormBuilder, private ticketAMService:TicketAModificarService
  ) {
    webSocketService.ngOnInit();
    library.addIcons(faComment);
    library.addIcons(faLock);
    library.addIcons(faBars);
    library.addIcons(faHourglass);
  }
  ngOnInit(): void {
    this.formulario = this.formBuilder.group({
      estado:['',[],[]]
    })
    this.authService.usuarioActual$.subscribe((usuario:Usuario) => {
      if (usuario) { 
        console.log("rol:",usuario.rol);
        this.usuarioActual = usuario;
        if(usuario.rol ==='AGENTE'){
        this.cargarTickets();
        }else if(usuario.rol ==='ADMIN' ){
          this.cargarTodosLosTickets();
        }
      }
    });
    
    this.ticketAMService.ticket$.subscribe(ticket =>{
      let ticketSeleccionado = this.tablatickets.find( obj => obj.numero_ticket === ticket.numero_ticket);
      ticketSeleccionado = ticket;
    })
  }


  abrirTicket(ticket:Ticket){
    this.router.navigate(['detalles']);
    this.ticketAMService.emitirTicket(ticket);
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
  mensajeConfirmacionPendiente(ticket:Ticket){
    this.mensajeAviso = true;
    this.ticketAMService.emitirTicket(ticket);
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
  
  cargarTodosLosTickets(){
    fetch(`${baseURL}/admin/reporte-tickets/obtener-tickets`,{
      method:"GET",
      headers:{
        "Authorization":`Bearer ${localStorage.getItem("token")}`,
        "Content-Type":"application/json"
      }
    })
    .then(response =>{
      if(!response.ok){
        throw new Error("No se pudieron obtener los tickets");
      }
      return response.json();
    })
    .then((response:Ticket[])=>{
      this.tablatickets = response;
      this.ticketService.emitirTickets(response);
    })
  }
  cargarTickets() {
    const token = localStorage.getItem("token");
    const estado = this.formulario.get("estado")?.value;
    if (!token) {
        alert("No se encontró un token. Inicia sesión nuevamente.");
        return;
    }

    fetch(`${baseURL}/admin/reporte-tickets/buscar-tickets?numeroUsuario=${this.usuarioActual.numero_usuario}&estadoTickets=${estado}`, {
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
        this.ticketService.emitirTickets(tickets);

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
        const ticketTemp = this.tablatickets.find(ticket => ticket.numero_ticket === numero_ticket);
        if(ticketTemp){
          ticketTemp.cerrado=true;
          ticketTemp.estado='CERRADO';
        }
        
      }else{
        console.error('Error al cerrar el ticket');
      }
    } )
    .then(ticket => console.log("Ticket cerrado",ticket))
    .catch(error => console.error("Error", error));
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
    else if(ticketEstado == "PENDIENTE"){
      return{
        "background-color":"PURPLE"
      }
    }
    else if(ticketEstado == "EN ESPERA"){
      return{
        "background-color":"var(--azul-claro)"
      }
    }
    return null;
  }
  obtenerRefTicketComentarios(numeroTicket:number){
    this.refTicketComentarios = numeroTicket;
  }
}
