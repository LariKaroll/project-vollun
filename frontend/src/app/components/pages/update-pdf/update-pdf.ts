import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Auth } from '../../../services/auth';
import { Router } from '@angular/router';
import { form } from '@angular/forms/signals';

@Component({
  selector: 'app-update-pdf',
  imports: [FormsModule],
  templateUrl: './update-pdf.html',
  styleUrl: './update-pdf.css',
})
export class UpdatePDF implements OnInit{
  novoPDF = {
    file: '',
    title: '',
    genre: '',
    sinopse: '',
    publicationDate: '',
    autor: ''
  }

  arquivoSelecionado: File | null = null;
  user: any;
  
  constructor(
    private auth : Auth,
    private router: Router
  ){}
  

  ngOnInit() {
    this.user = this.auth.getUserLogado();

    if (this.user && this.user.name) {
      this.novoPDF.autor = this.user.name; 
    }
  }

  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      this.arquivoSelecionado = file;
    }
  }

  fazerUpdatePdf() {
    if(!this.arquivoSelecionado) {
      alert("Por favor, selecione um arquivo PDF antes de enviar.");
      return;
    }
    const formData = new FormData();

    formData.append('file', this.arquivoSelecionado, this.arquivoSelecionado.name);

    formData.append('title', this.novoPDF.title);
    formData.append('genre', this.novoPDF.genre);
    formData.append('sinopse', this.novoPDF.sinopse);
    formData.append('publicationDate', this.novoPDF.publicationDate);
    formData.append('autor', this.novoPDF.autor);

    this.auth.updatePdf(formData).subscribe({
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
