import { Routes } from '@angular/router';
import { ReporteClientesComponent } from './reporte-clientes/reporte-clientes.component';
import { OrganizadorComputadorasComponent } from './organizador-computadoras/organizador-computadoras.component';
import { GenerarTicketComponent } from './generar-ticket/generar-ticket.component';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { DetallesComponent } from './detalles/detalles.component';
import { MensajePersonalizadoComponent } from './mensaje-personalizado/mensaje-personalizado.component';
import { UsuariosComponent } from './usuarios/usuarios.component';
import { RegistrarUsuarioComponent } from './registrar-usuario/registrar-usuario.component';

export const routes: Routes = [
    { path: 'reporte-clientes', component: ReporteClientesComponent },
    { path: 'organizador-computadoras', component: OrganizadorComputadorasComponent },
    { path: 'generar-tickets', component: GenerarTicketComponent },
    { path: 'app-component', component: AppComponent},
    { path: 'login', component: LoginComponent},
    { path: 'detalles', component: DetallesComponent},
    { path: 'mensaje', component: MensajePersonalizadoComponent},
    { path: 'usuarios', component: UsuariosComponent},
    { path: 'registrar-usuario',component: RegistrarUsuarioComponent}
];
