import { Component } from '@angular/core';
import { Login } from '../login/login';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [RouterLink ],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {}
