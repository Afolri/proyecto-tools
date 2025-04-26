import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Comentario } from './models/comentario';

@Injectable({
  providedIn: 'root'
})
export class ComentarioService {
  private comentarioService = new BehaviorSubject<Comentario>({
    contenido:"",
    numero_comentario:0
  });
  comentario$ = this.comentarioService.asObservable();

  constructor() { }

  emitirComentario(comentario:Comentario){
    this.comentarioService.next(comentario)
  }
}
