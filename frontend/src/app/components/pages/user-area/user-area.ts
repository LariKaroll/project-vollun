import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Auth } from '../../../services/auth';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-user-area',
  imports: [CommonModule],
  templateUrl: './user-area.html',
  styleUrl: './user-area.css',
})
export class UserArea implements OnInit {
  usuario: any;

  constructor(private authService: Auth, private router: Router) {}

  ngOnInit() {
    this.usuario = this.authService.getUserLogado();
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}

  

