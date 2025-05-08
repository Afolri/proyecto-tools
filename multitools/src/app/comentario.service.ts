import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Comentario } from './models/comentario';
import { ComentarioResponse } from './models/comentarioResponse';

@Injectable({
  providedIn: 'root'
})
export class ComentarioService {
  private comentarioService = new BehaviorSubject<ComentarioResponse>({
    numero_usuario:0,
    numero_ticket:0,
    numero_comentario:0,
    comentario:"",
  });
  comentario$ = this.comentarioService.asObservable();

  constructor() { }

  emitirComentario(comentario:ComentarioResponse){
    this.comentarioService.next(comentario)
  }
}
