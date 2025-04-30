import { Component, OnInit } from '@angular/core';
import { environment } from '../../environments/environment';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import {  FormBuilder, FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-registrar-usuario',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './registrar-usuario.component.html',
  styleUrl: './registrar-usuario.component.css'
})
export class RegistrarUsuarioComponent implements OnInit {
  urlBase = environment.URL_BASE;
  formulario!:FormGroup;

  constructor(private route:Router, private formBuilder:FormBuilder){
  }
  ngOnInit(): void {
    this.formulario = this.formBuilder.group({
      correo: ['',[],[]],
      nombre_usuario: ['',[],[]],
      password:['',[],[]],
      rol:['',[],[]]  
    })
  }
  onSubmit(){
    this.registrar();
  }
  registrar(){
    let token = localStorage.getItem("token");
    let correo = this.formulario.get("correo")?.value;
    let nombre_usuario = this.formulario.get("nombre_usuario")?.value;
    let password = this.formulario.get("password")?.value;
    let rol = this.formulario.get("rol")?.value;
    fetch(`${this.urlBase}/admin/auth/register`,{
      method:"POST",
      headers:{
        "Authorization":`Bearer ${token}`,
        "Content-Type":"application/json"
      },
      body:JSON.stringify({
        correo,
        nombre_usuario,
        password,
        rol
      })
    }).then(response =>{
      if(!response.ok){
        throw new Error ("no se pudo registrar");
      }
      this.route.navigate(["/usuarios"]);
    })
  }
}
