import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { GenerarTicketComponent, Ticket } from '../generar-ticket/generar-ticket.component';
import { AuthService } from '../services/auth.service';
import { Usuario } from '../login/login.component';
import { CommonModule } from '@angular/common';
import { Router} from '@angular/router';
import { environment } from '../../environments/environment';
import { NotificacionesResponse } from '../models/notificacionesResponse';


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
  @Input() notificacionesMostradas:NotificacionesResponse[] = [];
  @Output() notificacionEvent = new EventEmitter<number>();



  constructor(private authService:AuthService, private router:Router){}
  ngOnInit() {
    this.authService.usuarioActual$.subscribe(usuario =>{
      this.usuarioActual=usuario;
    })
    
  }
  


  leerNotificacion(numeroNotificacion:number){
    fetch(`${baseURL}/admin/reporte-tickets/abrir-notificacion?numeroNotificacion=${numeroNotificacion}&numeroUsuario=${this.usuarioActual.numero_usuario}`,
      {
        method: "PUT",
        headers: { "Content-Type": "application/json",
          "Authorization":`Bearer ${localStorage.getItem('token')}`
        }
      }
    ).then(response =>{
      if(!response.ok){
        throw new Error("Error al leer notificacion");
      }
      const noti = this.notificacionesMostradas.find(obj => obj.numero_notificacion === numeroNotificacion);
      noti!.visto = true;
    })
  }

  abrirDetallesNotificacion(numeroTicket:number){
    this.notificacionEvent.emit(numeroTicket);  
  }

}
