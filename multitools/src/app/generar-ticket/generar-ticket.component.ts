import { CommonModule } from '@angular/common';
import { Component,EventEmitter,Input, OnInit, Output} from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

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


@Component({
  selector: 'app-generar-ticket',
  imports: [ReactiveFormsModule,CommonModule],
  templateUrl: './generar-ticket.component.html',
  styleUrl: './generar-ticket.component.css'
})
export class GenerarTicketComponent implements OnInit  {

  //Para trabajar con modulo reactivo
  formularioTickets!:FormGroup;
  constructor(private formBuilder: FormBuilder){
   this.formularioTickets= this.formBuilder.group({
      numero_compra_cot:[null,[Validators.required,Validators.maxLength(20)]],
      codigo:['',[Validators.required,Validators.maxLength(30)]],
      nombre_identificador:['',[]],
      asunto:['',[Validators.required]],
      nombre:['',[Validators.required, Validators.minLength(2),Validators.maxLength(50),Validators.pattern("^[a-zA-Z ]+$")]],
      correo:['',[Validators.required, Validators.email, Validators.maxLength(100),Validators.pattern("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")]],
      telefono:['',[Validators.required,Validators.maxLength(10),Validators.pattern(`^[0-9]{10}$`)]],
      descripcion:['',[Validators.required,Validators.minLength(2),Validators.maxLength(100)]],
      ehs_approval:[false, [],[]]
    })
  }

  @Output() mostrarChange = new EventEmitter<boolean>();
  @Output() obtenerDatos = new EventEmitter<void>();
  @Output() mostrarMensajeAviso = new EventEmitter();
  @Output() aceptarActualizar = new EventEmitter();
  @Input() ticket:Ticket ={
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

  ngOnInit() {
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

    //llama a la función una vez generado el formulario
    this.configurarValidacionesCondicionales();
  }

  //Método para hacer requerido el nombre del identificador solo cuando el checkbox "checkPersonalizado" esta activo
  configurarValidacionesCondicionales() {
    const ehs_approval = this.formularioTickets.get('ehs_approval');
    const nombreIdentificadorControl = this.formularioTickets.get('nombre_identificador');
  
    ehs_approval?.valueChanges.subscribe(valor => {
      if (valor) {
        nombreIdentificadorControl?.setValidators([Validators.required]);
      } else {
        nombreIdentificadorControl?.clearValidators();
      }
      nombreIdentificadorControl?.updateValueAndValidity(); // Se actualiza la validación
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
  
  crearticket(){


    let codigo = this.formularioTickets.get('codigo')?.value;
    let nombre_identificador = this.formularioTickets.get('ehs_approval')?.value
    ? this.formularioTickets.get('nombre_identificador')?.value : 'numeroDeSerie';
  
    let numero_compra_cot= this.formularioTickets.get('numero_compra_cot')?.value;
    let nombre_cliente = this.formularioTickets.get('nombre')?.value;
    let correo = this.formularioTickets.get('correo')?.value;
    let telefono = this.formularioTickets.get('telefono')?.value;
    let asunto = this.formularioTickets.get('asunto')?.value;
    let descripcion = this.formularioTickets.get('descripcion')?.value;

    fetch("http://localhost:8080/admin/reporte-tickets/crear-ticket", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        codigo,
        nombre_identificador,
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
          return response.json().then(error =>{
            
            if(error.error != ""){
              const errorMessage = error.error;
              console.log(errorMessage)
            }
          })
        }
        return response.text().then(text => {
          // Si hay texto, lo parseamos a JSON, de lo contrario retornamos un objeto vacío
          return text ? JSON.parse(text) : {};
        });
      })
      .catch(error => console.error("Error:", error));
  }


  solicitaractualizacion(){
    this.mostrarMensajeAviso.emit(true);
  }
  actualizarTicket(){
    fetch(`http://localhost:8080/admin/reporte-tickets/actualizar-ticket`,{
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




}
