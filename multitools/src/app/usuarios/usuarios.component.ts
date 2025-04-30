import { Component, OnInit } from '@angular/core';
import { environment } from '../../environments/environment';
import { UsuarioResponseDTO } from '../models/usuario';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-usuarios',
  imports: [CommonModule, RouterLink],
  templateUrl: './usuarios.component.html',
  styleUrl: './usuarios.component.css'
})
export class UsuariosComponent implements OnInit {
  usuariosObtenidos:UsuarioResponseDTO[]=[];
  baseURL = environment.URL_BASE;
  ngOnInit(): void {
    this.obtenerUsuarios();
  }

  obtenerUsuarios(){
    fetch(`${this.baseURL}/admin/reporte-tickets/obtener-usuarios`,{
      method:"GET",
      headers:{
        "Authorization":`Bearer ${localStorage.getItem("token")}`,
        "Content-Type":"application/json"
      }
    })
    .then(response =>{
      if(!response.ok){
        throw new Error("No se pudo obtener los usuarios"); 
      }
      return response.json();
    })
    .then ((response:UsuarioResponseDTO[])=>{
      this.usuariosObtenidos = response;
    })
  }
  colorRol(rol:string){
    if(rol === 'ADMIN'){
      return { 'background-color': 'var(--azul)' }
    }else if(rol === 'AGENTE'){
      return {'background-color':'var(--azul-claro)'}
    }
    return {'background-color':'blue;'}
  }

}
