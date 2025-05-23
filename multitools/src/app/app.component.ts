import { CommonModule } from '@angular/common';
import { AfterViewChecked, AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router, RouterLink, RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AuthService } from './services/auth.service';
import { LoginComponent } from './login/login.component';
import{Usuario} from './login/login.component'
import { NotificacionesComponent } from './notificaciones/notificaciones.component';
import { ReporteClientesComponent } from './reporte-clientes/reporte-clientes.component';
import { Ticket } from './generar-ticket/generar-ticket.component';
import { WebSocketService } from './web-socket.service';
import { IMessage } from '@stomp/stompjs';
import { environment } from '../environments/environment';
import { TicketServiceService } from './ticket-service.service';
import { Comentario } from './models/comentario';
import { ComentarioResponse } from './models/comentarioResponse';
import { ComentarioService } from './comentario.service';
import { animacioncondicional } from './login/animacioncondicional';
import { filter, take } from 'rxjs';
import { NotificacionesResponse } from './models/notificacionesResponse';


const baseURL = `${environment.URL_BASE}`;

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    CommonModule,
    FormsModule,
    RouterLink,
    NotificacionesComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  standalone: true,
  animations:[animacioncondicional]
  

})
export class AppComponent implements OnInit{
  yainicializado:boolean= false;
  /**Se pasaran las notificaciones que se obtengan de una unica consulta a la base de datos */
  notificaciones:NotificacionesResponse[] = [];
  comentariosRaiz:Comentario[] = [];
  /**Variable que guarda si hay notificaciones pendientes */
  notificacionesSinLeer:boolean = false;
  mostrarNotificaciones:boolean = false;
  /**Aquí se guardara el ticket actual */
  ticketActual?:Ticket;
  ticketSeleccionadoEntidad?:Ticket;
  ticketSocket:Ticket[] =[];
  ticketsObtenidos:Ticket[]=[];
  title = 'MultiTools';
  usuarioActual!:Usuario;
  modo:'AGENTE'|'ADMIN'='AGENTE';

  constructor(private authService: AuthService, private router:Router, private webSocketService:WebSocketService,
    private ticketService:TicketServiceService, private comentarioService:ComentarioService
  ){

  }
  ngOnInit(): void {
      this.authService.usuarioActual$.subscribe(usuario =>{
      this.usuarioActual=usuario;
      if(usuario){
        
          this.modo = usuario?.rol;
          this.verNotificaciones();
          if(!this.yainicializado){
            this.yainicializado=true;
            this.webSocketService.suscribirse(`/topic/${this.usuarioActual.numero_usuario}`, (message:IMessage) =>{
              this.notificaciones.unshift(JSON.parse(message.body));
              this.notificacionesSinLeer=true;
            });
            this.ticketService.tickets$.subscribe(tickets =>{
              this.ticketsObtenidos = tickets;
            });
            this.webSocketService.suscribirse(`/topic/ticket/${usuario.numero_usuario}`,(message:IMessage)=>{
              const ticketSocket:Ticket = JSON.parse(message.body);
              this.ticketSocket.push(ticketSocket);
              console.log("tickets socket", ticketSocket);
            })
            this.webSocketService.suscribirse(`/comentario-topic/general`,(message:IMessage) =>{
              const comentario:ComentarioResponse = JSON.parse(message.body);
              this.comentarioService.emitirComentario(comentario);
            });
          }
          
          this.notificacionesPendientes();
        }
      })

  }
  logout(){
    this.authService.logout();
    this.router.navigate(['/login']);
    this.yainicializado=true;
  }
//  abrirDetallesNotificacion(numero_ticket:number){
//    this.ticketActual = this.obtenerTicketSeleccionado(numero_ticket);
//    const ticketJson = JSON.stringify(this.ticketActual);
//    let noti = this.notificaciones.find(obj => obj.numero_ticket === numero_ticket);
//    noti!.estado_notificacion = true;
//
//    if(this.notificaciones.find(obj => obj.estado_notificacion === true)){
//      this.notificacionesSinLeer = false;
//    }
//
//    localStorage.setItem("ticketseleccionado",ticketJson);
//    this.router.navigate(['detalles']);
//  }
  abrirNotificaciones(){
    if(this.mostrarNotificaciones){
      this.mostrarNotificaciones = false;
    }else{
      this.mostrarNotificaciones = true;
    }
  }

  obtenerTicketSeleccionado(ticketSeleccionado?: number): Ticket  {
    const tablaTicketstemp:Ticket[] = this.ticketsObtenidos;
    console.log("variable: ",tablaTicketstemp);
    let ticket = tablaTicketstemp?.find(obj => obj.numero_ticket === ticketSeleccionado);
    if(ticket){
      return this.ticketSeleccionadoEntidad = ticket;
    }else if(!ticket){
      ticket = this.ticketSocket?.find(obj => obj.numero_ticket === ticketSeleccionado);
      if(ticket){
        return ticket;
      }else{
        throw new Error ('Ticket no encontrado');
      }
    }else{
      throw new Error ('Ticket no encontrado');
    }
  }
  notificacionesPendientes(){
    const t = localStorage.getItem('token');
    fetch(`${baseURL}/admin/reporte-tickets/notificaciones-pendientes?numeroUsuario=${this.usuarioActual.numero_usuario}`,{
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
      this.notificacionesSinLeer = !response
    })
  }
  verNotificaciones(){
    console.log("ejecutado el obtener notificaciones");
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
