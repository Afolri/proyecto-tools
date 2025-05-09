import { CommonModule, NumberSymbol } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment';
import { FaIconLibrary, FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faInfo } from '@fortawesome/free-solid-svg-icons';
import { animacioncondicional } from './animacioncondicional';


export interface Usuario{
    numero_usuario:number,
    nombre_usuario:string,
    correo:string,
    rol:string
}

const baseURL = `${environment.URL_BASE}`;

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule,CommonModule, FontAwesomeModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
  animations:[animacioncondicional]
})
export class LoginComponent implements OnInit{
    registrorechazado:boolean = false;
    camposerroneos:boolean = false;
    correoerroneo:boolean = false;
    faInfo =faInfo;
    usuarioActual:Usuario={
        numero_usuario:0,
        nombre_usuario:"",
        correo:"",
        rol:""
    }
  

    token = localStorage.getItem('token');
    iniciarsesion!:FormGroup;
    constructor(private library:FaIconLibrary, private formBuilder: FormBuilder, private authService:AuthService, private router:Router){
        this.iniciarsesion = this.formBuilder.group({
            correo:['',[Validators.required,Validators.pattern("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")]],
            password:['',[Validators.required],[]]
        })
        library.addIcons(faInfo);
    }
  
  ngOnInit() {
    this.registrorechazado =false;
  }
  login() {
    let correo = this.iniciarsesion.get('correo')?.value;
    let password = this.iniciarsesion.get('password')?.value;

    fetch(`${baseURL}/admin/auth/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ correo, password })
    })
    .then(response => {
        if (!response.ok) {
            this.registrorechazado = true;
            setTimeout(() => {
                this.registrorechazado = false; // Ocultar la advertencia después de un tiempo
            }, 3000);
            throw new Error('Correo o contraseña incorrectos');
        }
        return response.json(); // Convertimos la respuesta a JSON
    })
    .then(data => {
        if (data.token) {  // Suponiendo que la API devuelve un objeto con { token: "..." }
            localStorage.setItem('token', data.token);
            this.obtenerUsuarioLoggeado();
            this.registrorechazado = false;
            this.router.navigate(['/reporte-clientes']);
        } else {
            throw new Error("No se recibió el token en la respuesta");
        }
    })
    .catch(error => {
        console.error("Error:", error.message);
    });
    }
    /**Este método verifica si los campos del formulario estan vacios */
    camposInvalidos(form: any): boolean {
         if(form && form.controls && Object.keys(form.controls).some(
            campo => form.controls[campo].value=== '' && form.controls[campo].touched)){
            this.camposerroneos = true;
            return true;
        }return false;
    }
    correoInvalido(form:any):boolean{
        const correo = form.controls?.['correo'];
        if(correo && correo.value != '' && correo.invalid && correo.touched){
            this.correoerroneo = true;
            return true;
        }return false;
    }
    correoEstilo(form:FormGroup){
        const correo = form.controls?.['correo'];
        if(correo.valid && form.controls?.['correo'].touched){
            return {"border":"green solid 2px"};
        }else{
            return {"":""}
        }
    }
    obtenerUsuarioLoggeado(){
        console.log("obteniendoUsuario");
        fetch(`${baseURL}/admin/reporte-tickets/obtener-credenciales`,{
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
            this.authService.setUsuario(response);
        })
    }
  
}
