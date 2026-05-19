import { Component, signal } from '@angular/core';
import { RouterOutlet, RouterLinkWithHref, RouterModule } from '@angular/router';
import { Login } from "./components/pages/login/login";
import { NavbarComponent } from './components/pages/navbar/navbar';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet,NavbarComponent],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('vollun');
}
