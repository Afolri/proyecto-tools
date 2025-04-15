import { Injectable, OnInit } from '@angular/core';
import { Client, IMessage } from '@stomp/stompjs';
import { BehaviorSubject } from 'rxjs';
import { number } from 'zod';
import { Notificaciones } from './reporte-clientes/reporte-clientes.component';
import { environment } from '../environments/environment';
import { Ticket } from './generar-ticket/generar-ticket.component';

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

  constructor() { }
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

  private initializeWebSocketConnection(retryCount = 0): void {
      const MAX_RETRIES = 5;
      const RETRY_DELAY = 5000; // ms
    
      const token = localStorage.getItem("token");
      const urlbroker = `${environment.URL_BASE_SOCKET}`;
      this.stompClient = new Client({
        brokerURL: urlbroker, // conexión WebSocket pura
        connectHeaders: {
          Authorization: `Bearer ${token}`
        },
        debug: (str) => {
          console.log(str);
        },
        reconnectDelay: RETRY_DELAY, // reconexión automática
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
        if (this.stompClient && this.stompClient.connected) {
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

    /**anterior
     * 
     *     suscribirse(ruta:string, callback:(message: IMessage) =>void){
      if(!this.conectado){
        const intervalo = setInterval(()=>{
          if(this.conectado){
            clearInterval(intervalo);
            this.stompClient?.subscribe(ruta, (message:IMessage) =>{
              callback(message);
            });
          }
        },500);
      }else{
        this.stompClient?.subscribe(ruta, (message: IMessage) =>{
          callback(message);
        });
      }
    }
     */
    
}
