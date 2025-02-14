import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CommonModule, FormsModule,RouterLink],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  standalone: true
  

})
export class AppComponent {
  title = 'MultiTools';
}
