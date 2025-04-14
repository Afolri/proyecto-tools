import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { GenerarTicketComponent, Ticket } from '../generar-ticket/generar-ticket.component';
import { AuthService } from '../services/auth.service';
import { Usuario } from '../login/login.component';
import { CommonModule } from '@angular/common';
import { Router} from '@angular/router';
import { environment } from '../../environments/environment';
import { Notificaciones } from '../reporte-clientes/reporte-clientes.component';


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
  @Input() notificacionesMostradas:Notificaciones[] = [];
  @Output() notificacionEvent = new EventEmitter<number>();



  constructor(private authService:AuthService, private router:Router){}
  ngOnInit() {
    this.authService.usuarioActual$.subscribe(usuario =>{
      this.usuarioActual=usuario;
    })
    
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
        /**this.verNotificaciones();*/
        this.abrirDetallesNotificacion(numeroTicketSeleccionado);
      }
    })
  }
  abrirDetallesNotificacion(numeroTicket:number){
    this.notificacionEvent.emit(numeroTicket);  
  }

}
