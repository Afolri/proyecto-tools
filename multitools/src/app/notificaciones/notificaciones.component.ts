import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { GenerarTicketComponent, Ticket } from '../generar-ticket/generar-ticket.component';
import { AuthService } from '../services/auth.service';
import { Usuario } from '../login/login.component';
import { CommonModule } from '@angular/common';
import { Router} from '@angular/router';
import { environment } from '../../environments/environment';

interface notificaciones{
  numero_notificacion:number;
  numero_ticket:number;
  fecha:Date,
  estado_notificacion:boolean,
  mensaje:string,
  estado:string,
  nombre_cliente:string
}
const baseURL = `${environment.URL_BASE}`;

@Component({
  selector: 'app-notificaciones',
  imports: [CommonModule],
  templateUrl: './notificaciones.component.html',
  styleUrl: './notificaciones.component.css',
  standalone: true,
  providers: [NotificacionesComponent]
})
export class NotificacionesComponent implements OnInit {
  usuarioActual!:Usuario;
  notificaciones:notificaciones[] = [];
  @Output() notificacionEvent = new EventEmitter<number>();

  constructor(private authService:AuthService, private router:Router){}
  ngOnInit() {
    this.authService.usuarioActual$.subscribe(usuario =>{
      this.usuarioActual=usuario;
    })
    this.verNotificaciones();
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
  leerNotificacion(numeroNotificacion:number, numeroTicketSeleccionado:number){
    fetch(`${baseURL}/admin/reporte-tickets/abrir-notificacion?numero-notificacion=${numeroNotificacion}`,
      {
        method: "PUT",
        headers: { "Content-Type": "application/json",
          "Authorization":`Bearer ${localStorage.getItem('token')}`
         }
      }
    ).then(response =>{
      if(response.ok){
        this.verNotificaciones();
        this.abrirDetallesNotificacion(numeroTicketSeleccionado);
      }
    })
  }
  abrirDetallesNotificacion(numeroTicket:number){
    this.notificacionEvent.emit(numeroTicket);  
  }

}
