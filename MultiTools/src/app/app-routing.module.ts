import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterModule, Routes } from '@angular/router';
import { ReporteClientesComponent } from './reporte-clientes/reporte-clientes.component';

const routes: Routes = [
  { path: 'reporte-clientes', component: ReporteClientesComponent }
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes),
    CommonModule
  ],
  exports:[RouterModule]
})
export class AppModule { }
