import { Injectable, OnInit } from '@angular/core';
import { Client, IMessage } from '@stomp/stompjs';
import { BehaviorSubject } from 'rxjs';
import { number } from 'zod';
import { Notificaciones } from './reporte-clientes/reporte-clientes.component';
import { environment } from '../environments/environment';
import { Ticket } from './generar-ticket/generar-ticket.component';
import { Comentario } from './models/comentario';
import { Usuario } from './login/login.component';
import { AuthService } from './services/auth.service';
import { ComentarioResponse } from './models/comentarioResponse';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService implements OnInit{
  

  /**Permite hacer la conexión con el socket */
  private stompClient?:Client;
  private conectado = false;

    /**Aquí se van a guardar las notificaciones por el socket */
    /**private notificaciones : BehaviorSubject<String[]> = new BehaviorSubject<String[]>([]);*/
  private notificacion?:Notificaciones;
  private usuario!:Usuario;
  constructor() {
  }
  ngOnInit(): void {
    
    this.initializeWebSocketConnection();
  }
  enviarTicket(ticket:Ticket, numerousuario:number){
    this.stompClient?.publish({
        destination:`/app/ticket/${numerousuario}`,
        body:JSON.stringify(
          ticket
        )
    });
  }

  solicitarmensaje(notificacion:Notificaciones){
    this.stompClient?.publish({
        destination:`/app/notificacion/${notificacion.numero_usuario}`,
        body:JSON.stringify(
          notificacion

        )
    });
  }

  enviarComentario(comentario:ComentarioResponse){
    this.stompClient?.publish({
      destination: `/app/comentario/general`,
      body:JSON.stringify(
        comentario
      )
    });
  }

  private initializeWebSocketConnection(retryCount = 0): void {
      const MAX_RETRIES = 5;
      const RETRY_DELAY = 5000; // ms
    
      const token = localStorage.getItem("token");
      const urlbroker = `${environment.URL_BASE_SOCKET}/admin/socket`;
      this.stompClient = new Client({
        brokerURL: urlbroker,
        debug: (str) => {
          console.log(str);
        },
        reconnectDelay: RETRY_DELAY,
        heartbeatIncoming: 10000,  
        heartbeatOutgoing: 10000, 
        onConnect: (frame) => {
          console.log('✅ Conectado al servidor WebSocket', frame);
          
          this.conectado = true;
          
        },
        onStompError: (frame) => {
          console.error('❌ Error STOMP', frame.headers['message'], frame.body);
        },
        onWebSocketError: (error) => {
          console.error('❌ Error en WebSocket', error);
          console.log("PANN",urlbroker);
        }
      });
    
      this.stompClient.activate();
  }
  suscribirse(ruta: string, callback: (message: IMessage) => void): void {
    const hacerSuscripcion = () => {
      if (this.stompClient && this.stompClient.connected ) {
        this.stompClient.subscribe(ruta, callback);
      } else {
        // Esperar hasta que esté conectado
        const intervalo = setInterval(() => {
          if (this.stompClient && this.stompClient.connected) {
            clearInterval(intervalo);
            this.stompClient.subscribe(ruta, callback);
          }
        }, 500);
      }
    };
  
    hacerSuscripcion();
  }   
}
