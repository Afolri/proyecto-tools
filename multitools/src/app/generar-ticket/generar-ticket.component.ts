import { CommonModule } from '@angular/common';
import { Component,EventEmitter,Input, OnInit, Output, RESPONSE_INIT} from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { environment } from '../../environments/environment';
import { Route, Router } from '@angular/router';
import { WebSocketService } from '../web-socket.service';
import { IMessage } from '@stomp/stompjs';
import { Notificaciones } from '../reporte-clientes/reporte-clientes.component';
import { tick } from '@angular/core/testing';


//La estructura cambia a:
export interface Ticket {
  numero_ticket: number;
  numero_producto: number;
  tipo_codigo:string;
  numero_compra_cot: string;
  asunto: string;
  numero_cliente:number;
  nombre_cliente: string;
  telefono:string;
  correo: string;
  descripcion: string;
  estado: string;
  cerrado: boolean;
  seleccionado: boolean;
  opciones:boolean;
  numero_agente:number;
  fecha: Date;
  hora:Date;

}
export interface TipoIdentificador{
  numero_identificador?:number,
  nombre_identificador?:string;
}

const baseURL = `${environment.URL_BASE}`;

@Component({
  selector: 'app-generar-ticket',
  imports: [ReactiveFormsModule,CommonModule],
  templateUrl: './generar-ticket.component.html',
  styleUrl: './generar-ticket.component.css'
})
export class GenerarTicketComponent implements OnInit  {
  identificadores!:TipoIdentificador[];
  //Para trabajar con modulo reactivo
  formularioTickets!:FormGroup;
  nombreBuscado:string |null =null;

  @Output() mostrarChange = new EventEmitter<boolean>();
  @Output() obtenerDatos = new EventEmitter<void>();
  @Output() mostrarMensajeAviso = new EventEmitter();
  @Output() aceptarActualizar = new EventEmitter();
  @Input() ticket :Ticket ={
    numero_ticket:0,
    numero_producto: 0,
    tipo_codigo:"",
    numero_compra_cot: "",
    asunto:"",
    numero_cliente:0,
    nombre_cliente: "",
    telefono:"",
    correo: "",
    descripcion: "",
    estado: "",
    cerrado: false,
    seleccionado: false,
    opciones:false,
    numero_agente:0,
    fecha: new Date,
    hora: new Date ('hh:mm:ss')
  }
  @Input() modo: 'crear'|'editar' ='crear';
  ticketGuardar: Ticket = {
    numero_ticket:0,
    numero_producto: 0,
    tipo_codigo:"",
    numero_compra_cot: "",
    asunto:"",
    numero_cliente:0,
    nombre_cliente: "",
    telefono:"",
    correo: "",
    descripcion: "",
    estado: "",
    cerrado: false,
    seleccionado: false,
    opciones:false,
    numero_agente:0,
    fecha: new Date,
    hora: new Date
  };

  constructor(private formBuilder: FormBuilder, private route:Router,
    private webSocketService:WebSocketService
  ){
    webSocketService.ngOnInit();
    this.formularioTickets= this.formBuilder.group({
       numero_compra_cot:[null,[Validators.required,Validators.maxLength(20)]],
       codigo:['',[Validators.required,Validators.maxLength(30)]],
       codigo2:['',[]],
       numero_identificador:['',[]],
       asunto:['',[Validators.required]],
       nombre:['',[Validators.required, Validators.minLength(2),Validators.maxLength(50),Validators.pattern("^[a-zA-Z ]+$")]],
       correo:['',[Validators.required, Validators.email, Validators.maxLength(100),Validators.pattern("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")]],
       telefono:['',[Validators.required,Validators.maxLength(10),Validators.pattern(`^[0-9]{10}$`)]],
       descripcion:['',[Validators.required,Validators.minLength(2),Validators.maxLength(100)]],
       ehs_approval:[false, [],[]]
     })
   }
  ngOnInit() {
    console.log("init de generar-ticket");
    if (this.modo === 'editar' && this.ticket) {
      this.formularioTickets.setValue({
        numero_compra_cot: this.ticket.numero_compra_cot,
        asunto: this.ticket.asunto,
        nombre: this.ticket.nombre_cliente,
        correo: this.ticket.correo,
        telefono: this.ticket.telefono,
        descripcion: this.ticket.descripcion
        
      })
    }
    this.obtenerIdentificadores();

    //llama a la función una vez generado el formulario
    this.configurarValidacionesCondicionales();
    this.webSocketService.suscribirse('/topic',(message:IMessage)=>{
     
    });
  }

  //Método para hacer requerido el nombre del identificador solo cuando el checkbox "checkPersonalizado" esta activo
  configurarValidacionesCondicionales() {
    const numero_identificador = this.formularioTickets.get('numero_identificador');
    const codigo2 = this.formularioTickets.get('codigo2');
    const validar = this.formularioTickets.get('ehs_approval');
  
    // Suscripción para cambios en numero_identificador
    numero_identificador?.valueChanges.subscribe(valor => {
      const idConvertido = Number(valor); // Convertimos a número por seguridad
      
      if (idConvertido === 4) {
        codigo2?.setValidators([Validators.required, Validators.maxLength(30)]);
      } else {
        codigo2?.clearValidators();
      }
  
      codigo2?.updateValueAndValidity();
    });
  
    // Suscripción para cambios en ehs_approval
    validar?.valueChanges.subscribe(valor => {
      if (!valor) {
        codigo2?.clearValidators();
        codigo2?.updateValueAndValidity();
      }
    });
  }
  
  
  
  //Metodo para trabajar con el envio del formulario reactivo:
  onSubmit(){
    if(this.formularioTickets.valid){
      console.log('Datos del formulario', this.formularioTickets.value);
      this.crearticket();
    }else{
      console.log('El formulario no es válido')
    }
  }
  cancelarFormulario(){
    this.mostrarChange.emit(false);
  }
  editarTicket(){
    this.obtenerDatos.emit();
  }
  
  async crearticket(){


    let codigo = this.formularioTickets.get('codigo')?.value + 
    (this.formularioTickets.get('ehs_approval')?.value ? 
    " " + this.formularioTickets.get('codigo2')?.value : "");
    let numero_identificador = this.formularioTickets.get('ehs_approval')?.value
    ? this.formularioTickets.get('numero_identificador')?.value : '2';
    let numero_compra_cot= this.formularioTickets.get('numero_compra_cot')?.value;
    let nombre_cliente = this.formularioTickets.get('nombre')?.value;
    let correo = this.formularioTickets.get('correo')?.value;
    let telefono = this.formularioTickets.get('telefono')?.value;
    let asunto = this.formularioTickets.get('asunto')?.value;
    let descripcion = this.formularioTickets.get('descripcion')?.value;

    const resultado = await fetch(`${baseURL}/admin/reporte-tickets/crear-ticket`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        codigo,
        numero_identificador,
        numero_compra_cot,
        nombre_cliente,
        correo,
        telefono,
        asunto,
        descripcion
      })
    })
      .then(response => {
        if (!response.ok) {
          response.json().then(error =>{
            
            if(error.error != ""){
              const errorMessage = error.error;
              console.log(errorMessage)
             
            }
          })
          return null;
        }
        return  response.json();
      })
      .catch(error =>{
        console.error("Error:", error);
        return null;
      });
      const notificacion:Notificaciones =resultado['notificacionResponseDTO'];
      const ticket:Ticket = resultado['ticketResponseDTO'];
      this.webSocketService.solicitarmensaje(notificacion);
      this.webSocketService.enviarTicket(ticket,notificacion.numero_usuario);
      this.route.navigate(['mensaje'])
      
  }


  solicitaractualizacion(){
    this.mostrarMensajeAviso.emit(true);
  }
  actualizarTicket(){
    fetch(`${baseURL}/admin/reporte-tickets/actualizar-ticket`,{
      method: 'PUT',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({
        ticket: {
          numero_ticket: this.ticket.numero_ticket,
          asunto: this.formularioTickets.get('asunto')?.value,
          descripcion: this.formularioTickets.get('descripcion')?.value
      },
      productoticket: {
          numero_producto: this.ticket.numero_producto,
          numero_serie_modelo: this.formularioTickets.get('numero_serie_modelo')?.value,
          numero_compra_cot: this.formularioTickets.get('numero_compra_cot')?.value
      },
      cliente: {
          numero_cliente: this.ticket.numero_cliente,
          nombre_cliente:this.formularioTickets.get('nombre')?.value,
          correo: this.formularioTickets.get('correo')?.value,
          telefono: this.formularioTickets.get('telefono')?.value
      }
      }),
    })
   .then(response => {
     if(response.ok){
       this.mostrarChange.emit(false);
       this.editarTicket();
       console.log('Ticket actualizado correctamente');
     }else{
       console.error('Error al actualizar el ticket');
     }
   })
   .catch(error => console.error("Error", error));
  }

  camposInvalidos(form: any): boolean {
    return form && form.controls &&
      Object.keys(form.controls).some(
        campo => form.controls[campo].invalid && form.controls[campo].touched
      );
  }
  contieneNumeros(texto: string | null | undefined): boolean {
    if(!texto) return false;
    return /\d/.test(texto); // Devuelve true si encuentra un número
  }


  obtenerIdentificadores() {
    fetch(`${baseURL}/admin/reporte-tickets/obtener-identificadores`, {
      method: "GET",
      headers: { "Content-Type": "application/json" }
    })
      .then(response => {
        if (!response.ok) {
          throw new Error("Hubo un error al obtener los identificadores");
        }
        return response.json();
      })
      .then((identificadores: TipoIdentificador[]) => {
        this.identificadores = identificadores;
        console.log("Identificadores obtenidos:", identificadores);
      })
      .catch(error => {
        console.error("Error en la solicitud:", error);
      });
  }
  buscarNombreIdentificador(){
    const numero:number = +this.formularioTickets.get('numero_identificador')?.value;
    console.log("numeor a buscar",numero);
    const obj = this.identificadores.find(obj => obj.numero_identificador === numero );
    console.log("numero identificador", obj);
    if(obj?.nombre_identificador){
      this.nombreBuscado = obj?.nombre_identificador;
    }

  }


}
