import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Auth } from '../../../services/auth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-pdf',
  imports: [FormsModule],
  templateUrl: './update-pdf.html',
  styleUrl: './update-pdf.css',
})
export class UpdatePDF {
  novoPDF = {
    file: '',
    title: '',
    genre: '',
    sinopse: '',
    publicationDate: '',
    autor: ''
  }

  user: any;

  constructor(
    private auth : Auth,
    private router: Router
  ){}

  ngOnInit() {
    this.user = this.auth.getUserLogado();
  }

  fazerUpdatePdf() {

    this.auth.updatePdf(this.novoPDF).subscribe({
      next: (res) => {
        console.log("PDF registrado com sucesso!", res);
        alert("Acesse a area de livros!");
        this.router.navigate(['/'])
      },
      error: (err) => {
        console.error("Erro ao registrar o pdf: ", err);
        alert("Erro ao registrar o pdf");
      },
    })
  }
}
