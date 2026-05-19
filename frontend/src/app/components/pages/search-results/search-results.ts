import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { GoogleService } from '../../../services/GoogleService';

@Component({
  selector: 'app-search-results',
  imports: [],
  templateUrl: './search-results.html',
  styleUrl: './search-results.css',
})
export class SearchResults implements OnInit {
  livros: any[] = [];
  termoBusca: string = '';

  constructor(
    private route: ActivatedRoute,
    private googleService: GoogleService
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.termoBusca = params['q'] || '';
      if (this.termoBusca) {
        this.pesquisar();
      }
    });
  }

  pesquisar() {
    this.googleService.buscarNoGoogle(this.termoBusca).subscribe({
      next: (res) => {
        this.livros = res;
        console.log("Livros vindos do Google através do Back:", res);
      },
      error: (err) => console.error("Erro na busca externa", err)
    });
  }
}
