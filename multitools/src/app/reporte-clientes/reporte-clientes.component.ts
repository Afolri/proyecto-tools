import { CommonModule } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
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
import { Router } from '@angular/router';
import { environment } from '../../environments/environment';
import { Client, IMessage } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { BehaviorSubject } from 'rxjs';
import { WebSocketService } from '../web-socket.service';

export interface Notificaciones{
  numero_notificacion:number;
  numero_ticket:number;
  numero_usuario:number
  fecha:Date,
  hora:Date,
  estado_notificacion:boolean,
  mensaje:string,
  estado:string,
  nombre_cliente:string,
}

const baseURL = `${environment.URL_BASE}`;

@Component({
  selector: 'app-reporte-clientes',
  imports: [
    CommonModule,
    FormsModule,
    MensajeAvisoComponent,
    FontAwesomeModule,
    NotificacionesComponent,
    EditarTicketComponent,
  ],
  templateUrl: './reporte-clientes.component.html',
  styleUrls: ['./reporte-clientes.component.css']
})

export class ReporteClientesComponent implements OnInit {
  /**Donde se guardaraon los tickets que se pasen por el socket */
  ticketsSocket:Ticket[] = [];
  /**Se pasaran las notificaciones que se obtengan de una unica consulta a la base de datos */
  notificaciones:Notificaciones[] = [];
  /**Variable que guarda si hay notificaciones pendientes */
  notificacionesSinLeer:boolean = false;
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



  constructor(library: FaIconLibrary, private authService: AuthService, private router: Router,
    private webSocketService:WebSocketService
  ) {
    webSocketService.ngOnInit();
    library.addIcons(faComment);
    library.addIcons(faLock);
    library.addIcons(faBars);
  }
  ngOnInit(): void {
    this.authService.usuarioActual$.subscribe(usuario => {
      if (usuario) { 
        this.usuarioActual = usuario;
        this.cargarTickets();
        this.verNotificaciones();
        this.webSocketService.suscribirse(`/topic/${this.usuarioActual.numero_usuario}`, (message:IMessage) =>{
          console.log("Resultado",JSON.parse(message.body));
          this.notificaciones.unshift(JSON.parse(message.body));
          this.notificacionesSinLeer=true;
        })
        this.webSocketService.suscribirse(`/topic/ticket/${this.usuarioActual.numero_usuario}`,(message:IMessage) =>{
          this.ticketsSocket.push(JSON.parse(message.body));
        })
      }
    });
    this.notificacionesPendientes();
    
  }

  ngAfterViewInit() {
    // Se llama tanto en carga inicial como al volver con botón atrás en iOS
    window.addEventListener('pageshow', () => {
      window.dispatchEvent(new Event('resize'));
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
    }else if(!ticket){
      this.cargarTickets();
      ticket = this.ticketsSocket?.find(obj => obj.numero_ticket === ticketSeleccionado);
      if(ticket){
        return ticket;
      }else{
        throw new Error ('Ticket no encontrado');
      }
    }else{
      throw new Error ('Ticket no encontrado');
    }
  }
  

  abrirDetallesNotificacion(numero_ticket:number){
    console.log("se ejecuto el metodo padre");
    this.ticketActual = this.obtenerTicketSeleccionado(numero_ticket);
    const ticketJson = JSON.stringify(this.ticketActual);
    localStorage.setItem("ticketseleccionado",ticketJson);
    this.router.navigate(['detalles']);
  }

  notificacionesPendientes(){
    const t = localStorage.getItem('token');
    fetch(`${baseURL}/admin/reporte-tickets/notificaciones-pendientes`,{
      method: 'GET',
      headers:{
        "Content-Type": "application/json",
        "Authorization": `Bearer ${t}`
      }
    }).then(response =>{
      if(!response.ok){
        throw new Error(`Error ${response.status}: No se pudieron obtener las notificaciones.`);
      }
      return response.json();
    })
    .then (response =>{
      this.notificacionesSinLeer = response
    })
  }

  verNotificaciones(){
    fetch(`${baseURL}/admin/reporte-tickets/obtenerNotificaciones?numeroUsuario=${this.usuarioActual.numero_usuario}`,
      {
        method: "GET",
        headers: { "Content-Type": "application/json",
          "Authorization":`Bearer ${localStorage.getItem('token')}`
         }
      }
    )
    .then(response => response.json())
    .then(notificaciones => this.notificaciones = notificaciones);
  }
}
