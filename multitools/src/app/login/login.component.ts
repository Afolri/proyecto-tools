import { CommonModule, NumberSymbol } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment.development';


export interface Usuario{
    numero_usuario:number,
    nombre:string,
    correo:string,
    rol:string
}

const baseURL = `${environment.URL_BASE}`;

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule,CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{

    usuarioActual:Usuario={
        numero_usuario:0,
        nombre:"",
        correo:"",
        rol:""
    }
  

    token = localStorage.getItem('token');
    iniciarsesion!:FormGroup;
    constructor(private formBuilder: FormBuilder, private authService:AuthService, private router:Router){
        this.iniciarsesion = this.formBuilder.group({
            correo:['',[Validators.required],[]],
            password:['',[Validators.required],[]]
        })
    }
  
  ngOnInit() {
  }
  login() {
    let correo = this.iniciarsesion.get('correo')?.value;
    let password = this.iniciarsesion.get('password')?.value;

    fetch(`http://${baseURL}/admin/auth/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ correo, password })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Correo o contraseña incorrectos');
        }
        return response.json(); // Convertimos la respuesta a JSON
    })
    .then(data => {
        if (data.token) {  // Suponiendo que la API devuelve un objeto con { token: "..." }
            localStorage.setItem('token', data.token);
            console.log("Token guardado:", data.token);
            this.obtenerUsuarioLoggeado();
            return this.router.navigate(['/reporte-clientes']);
        } else {
            throw new Error("No se recibió el token en la respuesta");
        }
    })
    .catch(error => {
        console.error("Error:", error.message);
    });
}
obtenerUsuarioLoggeado(){
    fetch(`http://${baseURL}/admin/reporte-tickets/obtener-credenciales`,{
        method:"GET",  
        headers: {
            "Content-Type": "application/json",
             "Authorization": `Bearer ${localStorage.getItem('token')}` 
        }
    

    }).then(response =>{
        if(response.ok){
            return response.json();
            
        }
        return console.error("Error", "no se pudieron obtener los datos de usuario");
    }).then(response =>{
        console.log("Usuario",response);
        this.authService.setUsuario(response);
    })
}
  
}
