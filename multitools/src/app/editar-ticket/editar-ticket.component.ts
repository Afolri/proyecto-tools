import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { Ticket } from '../generar-ticket/generar-ticket.component';
import { environment } from '../../environments/environment';
import { Comentario } from '../models/comentario';
import { ComentarioTicket } from '../models/comentarioTicket';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { Usuario } from '../login/login.component';
import { WebSocketService } from '../web-socket.service';
import { ComentarioResponse } from '../models/comentarioResponse';
import { IMessage } from '@stomp/stompjs';
import { ComentarioService } from '../comentario.service';

@Component({
  selector: 'app-editar-ticket',
  imports: [ReactiveFormsModule],
  templateUrl: './editar-ticket.component.html',
  styleUrl: './editar-ticket.component.css',
  standalone:true
})
export class EditarTicketComponent implements OnInit {
  comentarFormulario!:FormGroup;
  comentariosObtenidos:Comentario[]=[];
  @Input() ticket!: Ticket;
  @Input() numeroTicket!:number;
  @Output() salirFormulario = new EventEmitter<Ticket>();
  usuarioActual!:Usuario;

  ngOnInit(): void {
    this.obtenerComentarios();
    this.authService.usuarioActual$.subscribe( usuario =>{
      if(usuario){
        this.usuarioActual = usuario;
      }
    });
    this.comentarioService.comentario$.subscribe(comentario =>{
      /**this.comentariosRaiz= comentario;*/
      this.comentariosObtenidos.push(comentario);
    });
  }
  constructor(private authService:AuthService,private formBuilder:FormBuilder,
    private webSocket:WebSocketService, private comentarioService:ComentarioService
  ){
    this.comentarFormulario = this.formBuilder.group({
      comentarioTextArea:['',[],[]]
    })
    
  }
  onSubmit(){
    this.comentar();
  }
  cancelar(){
    this.salirFormulario.emit();
  }

  obtenerComentarios(){
    const token = localStorage.getItem("token");
    fetch(`${environment.URL_BASE}/admin/reporte-tickets/obtener-comentario-ticket?numero-ticket=${this.numeroTicket}`,{
      method:"GET",
      headers:{
        "Authorization":`Bearer ${token}`
      }
    })
    .then(response =>{
      if(!response.ok){
        throw new Error("Error al obtener los comentarios");
      }
      return response.json();
    })
    .then((response:Comentario[]) =>{
      this.comentariosObtenidos=response;
    })
  }

  async comentar(){
    const token = localStorage.getItem("token");
    const numero_ticket = this.ticket.numero_ticket;
    const comentario = this.comentarFormulario.get("comentarioTextArea")?.value;
    const numero_usuario = this.usuarioActual.numero_usuario;

    const valor:any = await fetch(`${environment.URL_BASE}/admin/reporte-tickets/comentar-ticket`,{
      method:"POST",
      headers:{
        "Content-Type":"application/json",
        "Authorization":`Bearer ${token}`
      },
      body:JSON.stringify({
        comentario,
        numero_usuario,
        numero_ticket
      })
    }).then(response => {
      if(!response.ok){
        throw new Error("error al comentar");
      }
      /**this.obtenerComentarios();*/
      return response.json();
    
    })
    .then(reponse => {
      return reponse;
    })
    const comentarioResponse:ComentarioResponse = valor;
    this.webSocket.enviarComentario(numero_usuario,comentarioResponse);
  }

}
