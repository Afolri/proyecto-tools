import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink, RouterOutlet } from '@angular/router';
import { environment } from '../../environments/environment.development';

interface Producto{
  id: number;
  noserie: string;
  estado_producto: string;
  id_marca:number;
  id_procesador:number;
  ram:string
  fecha_configuracion:Date;
  usuario:string;
}

interface Marca{
  id_marca:number;
  nombre_marca:string;
}

interface Procesador{
  id_procesador:number;
  procesador:string;
}

const baseURL = `${environment.URL_BASE}`;
@Component({
  selector: 'app-organizador-computadoras',
  imports: [CommonModule, FormsModule],
  templateUrl: './organizador-computadoras.component.html',
  styleUrl: './organizador-computadoras.component.css'
})
export class OrganizadorComputadorasComponent implements OnInit{
  
  productos: Producto[] = [];
  marcas: Marca[] = [];
  procesadores: Procesador[] = [];
  productoGuardar: Producto = {
    id: 0,
    noserie: '',
    estado_producto: '',
    id_marca:0,
    id_procesador:0,
    ram:'',
    fecha_configuracion:new Date(),
    usuario:''
  }

  ngOnInit(){
    this.cargarProductos();
    this.cargarMarcas();
    this.cargarProcesadores();
  }
  cargarProductos(){
    fetch(`${baseURL}/admin/inicio/obtenerproductos`)
    .then(respuesta => respuesta.json())
    .then((productos:Producto[])=>{
      console.log('Productos recibidos:',productos);
      this.productos = productos;
    });
  }

  enviarFormulario(event: Event) {
    event.preventDefault();

    let noserie = this.productoGuardar.noserie;
    let estado_producto = this.productoGuardar.estado_producto;
    let id_marca = this.productoGuardar.id_marca;
    let id_procesador = this.productoGuardar.id_procesador;
    let ram = this.productoGuardar.ram;


    const fecha = new Date(this.productoGuardar.fecha_configuracion);
  
    // Verifica si la fecha es válida
    if (isNaN(fecha.getTime())) {
      console.error("Fecha inválida");
      return;
    }

  let fecha_configuracion = fecha.toISOString().split("T")[0].split("-").reverse().join("/");

    let usuario = this.productoGuardar.usuario;

    fetch(`${baseURL}/admin/inicio/guardarproducto`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        noserie,
        estado_producto,
        id_marca,
        id_procesador,
        ram,
        fecha_configuracion,
        usuario
      })
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Error en la respuesta del servidor');
      }
      return response.json();
    })
    .then(text => {
      try {
        return text ? JSON.parse(text) : {};
      } catch (error) {
        console.warn("La respuesta no es JSON, pero se guardó correctamente.");
        return {};
      }
    })
    .then(() => {
      alert("Producto guardado correctamente");
      this.cargarProductos(); 
    })
    .catch(error => console.error("Error", error));
  }

  cargarMarcas() {
    fetch(`${baseURL}/admin/inicio/obtenerMarcasComputadoras`)
      .then(response => response.json())
      .then((marcas: Marca[]) => {
        console.log('Marcas recibidas:', marcas);
        this.marcas = marcas;
      })
      .catch(error => console.error("Error obteniendo marcas:", error));
  }

  cargarProcesadores(){
    fetch(`${baseURL}/admin/inicio/obtenerProcesadores`)
    .then(response => response.json())
    .then((procesadores: Procesador[])=>{
      console.log('Procesadores recibidos:', procesadores);
      this.procesadores = procesadores;
    })
  }


}
