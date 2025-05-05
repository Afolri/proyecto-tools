import { Component, input, OnInit, ViewChild } from '@angular/core';
import { environment } from '../../environments/environment';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import {  FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { LoginComponent } from '../login/login.component';
import { animacioncondicional } from '../login/animacioncondicional';

@Component({
  selector: 'app-registrar-usuario',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './registrar-usuario.component.html',
  styleUrl: './registrar-usuario.component.css',
  animations:[animacioncondicional]
})
export class RegistrarUsuarioComponent implements OnInit {
  urlBase = environment.URL_BASE;
  formulario!:FormGroup;
  registrorechazado:boolean = false;

  constructor(private route:Router, private formBuilder:FormBuilder){
  }
  ngOnInit(): void {
    this.formulario = this.formBuilder.group({
      correo: ['',[Validators.email, Validators.required],[]],
      nombre_usuario: ['',[Validators.pattern("^[a-zA-Z ]+$"), Validators.required],[]],
      password:['',[Validators.required],[]],
      password_confirmar:['',[Validators.required],[]],
      rol:['',[Validators.required],[]]  
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
    let password_confirmar = this.formulario.get("password_confirmar")?.value;
    let rol = this.formulario.get("rol")?.value;
    if(password === password_confirmar){
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
          password_confirmar,
          rol
        })
      }).then(response =>{
        if(!response.ok){
          this.registrorechazado = true;
          setTimeout(() => {
              this.registrorechazado = false; // Ocultar la advertencia después de un tiempo
          }, 3000);
          throw new Error ("no se pudo registrar");
        }
        this.route.navigate(["/usuarios"]);
      });
    }else{
      throw new Error("Contraseñas diferentes");
    }
  }
}
