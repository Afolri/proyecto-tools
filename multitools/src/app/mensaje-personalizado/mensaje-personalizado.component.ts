import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-mensaje-personalizado',
  imports: [],
  templateUrl: './mensaje-personalizado.component.html',
  styleUrl: './mensaje-personalizado.component.css'
})
export class MensajePersonalizadoComponent implements OnInit {
  mensaje?:string;
  ngOnInit(): void {
    this.mensaje = "guardado con éxito"
  }
  
}
