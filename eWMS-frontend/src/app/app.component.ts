import { Component } from '@angular/core';
import { RouterOutlet,RouterModule } from '@angular/router';
import { AuthService } from './auth.service';
import { HeaderComponent } from "./header/header.component";



@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterModule, HeaderComponent,],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'eWMS-frontend';



 
}
