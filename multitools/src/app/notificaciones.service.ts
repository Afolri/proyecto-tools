import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { NotificacionesResponse } from './models/notificacionesResponse';

@Injectable({
  providedIn: 'root'
})
export class NotificacionesService {
  private notificacionService =new  BehaviorSubject<NotificacionesResponse>({
    numero_notificacion:0,
    numero_usuario:0,
    fecha: new Date(),
    hora: new Date(),
    visto:false,
    mensaje:''
  });
  constructor() { }
}
