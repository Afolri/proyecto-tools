import { Component, OnInit } from '@angular/core';
import { GenerarTicketComponent, Ticket } from '../generar-ticket/generar-ticket.component';
import { AuthService } from '../services/auth.service';
import { Usuario } from '../login/login.component';
import { CommonModule } from '@angular/common';
import { Router} from '@angular/router';
import { environment } from '../../environments/environment.development';

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
  imports: [GenerarTicketComponent, CommonModule],
  templateUrl: './notificaciones.component.html',
  styleUrl: './notificaciones.component.css',
  standalone: true,
  providers: [NotificacionesComponent]
})
export class NotificacionesComponent implements OnInit {
  usuarioActual!:Usuario;
  notificaciones:notificaciones[] = [];

  constructor(private authService:AuthService, private router:Router){}
  ngOnInit() {
    this.authService.usuarioActual$.subscribe(usuario =>{
      this.usuarioActual=usuario;
    })
    this.verNotificaciones();
  }
  

  verNotificaciones(){
    fetch(`http://${baseURL}/admin/reporte-tickets/obtenerNotificaciones?numeroUsuario=${this.usuarioActual.numero_usuario}`,
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
  leerNotificacion(numeroNotificacion:number){
    fetch(`http://${baseURL}/admin/reporte-tickets/abrir-notificacion?numero-notificacion=${numeroNotificacion}`,
      {
        method: "PUT",
        headers: { "Content-Type": "application/json",
          "Authorization":`Bearer ${localStorage.getItem('token')}`
         }
      }
    ).then(response =>{
      if(response.ok){
        this.verNotificaciones();
        this.abrirDetallesNotificacion();
        console.log("notificacion vista")
      }
    })
  }

  abrirDetallesNotificacion(){
    this.router.navigate(['detalles']);
  }

}
