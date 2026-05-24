package br.com.vollun.model.dto;

import br.com.vollun.model.entity.Book;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record BookRequestDTO(
        @NotBlank(message = "O title é obrigatório")
        String title,

        @NotBlank(message = "O Genero é obrigatório")
        String genre,

        @NotBlank(message = "A sinopse é obrigatório")
        String sinopse,

        @NotBlank(message = "A url do livro e obrigatório")
        String UrlPdf,

        @NotBlank(message = "A data de publicação é obrigatório")
        LocalDate publicationDate,

        @NotBlank(message = "O Autor é obrigatório")
        String idAutor
) {

    public BookRequestDTO(Book book) {
        this(
                book.getTitle(),
                book.getSinopse(),
                book.getGenre(),
                book.getIdAutor(),
                book.getPublicationDate(),
                book.getUrlPdf()
        );
    }
}
