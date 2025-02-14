import { Routes } from '@angular/router';
import { ReporteClientesComponent } from './reporte-clientes/reporte-clientes.component';
import { OrganizadorComputadorasComponent } from './organizador-computadoras/organizador-computadoras.component';
import { GenerarTicketComponent } from './generar-ticket/generar-ticket.component';

export const routes: Routes = [
    { path: 'reporte-clientes', component: ReporteClientesComponent },
    { path: 'organizador-computadoras', component: OrganizadorComputadorasComponent },
    { path: 'generar-tickets', component: GenerarTicketComponent }
];
