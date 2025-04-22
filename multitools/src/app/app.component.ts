import { CommonModule } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router, RouterLink, RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AuthService } from './services/auth.service';
import { LoginComponent } from './login/login.component';
import{Usuario} from './login/login.component'
import { ViewportFixService } from './services/viewport-fix.service';
@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CommonModule, FormsModule,RouterLink, LoginComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  standalone: true
  

})
export class AppComponent implements OnInit {
  title = 'MultiTools';
  usuarioActual!:Usuario;
  modo:'AGENTE'|'ADMIN'='AGENTE';


  constructor(private authService: AuthService, private router:Router, private viewportFix: ViewportFixService){

  }
  ngAfterViewInit(): void {
    this.viewportFix.initFixOnPageShow();
  }
  ngOnInit(): void {
    this.authService.usuarioActual$.subscribe(usuario =>{
      this.usuarioActual=usuario;
      this.modo= usuario?.rol;
    })
  }
  logout(){
    this.authService.logout();
    this.router.navigate(['/login']);
  }
  
  
}
