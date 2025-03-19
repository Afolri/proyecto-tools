import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private usuarioActual = new BehaviorSubject<any>(null);
  usuarioActual$ = this.usuarioActual.asObservable();

  constructor() {
    const usuario = localStorage.getItem('usuarioActual');
    if(usuario){
      this.usuarioActual.next(JSON.parse(usuario));
    }
   }

   setUsuario(usuario:any){
    this.usuarioActual.next(usuario);
    localStorage.setItem('usuarioActual',JSON.stringify(usuario));
   }
   getUsuario(){
    return this.usuarioActual.getValue();
   }

   logout(){
    this.usuarioActual.next(null);
    localStorage.removeItem('usuarioActual');
    localStorage.removeItem('token');
   }
}
