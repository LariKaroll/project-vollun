package br.com.vollun.model.dto;

import br.com.vollun.model.entity.Book;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record BookResponseDTO(
        UUID id,
        String title,
        String genre,
        String sinopse,
        String urlPdf,
        LocalDate publicationDate,
        LocalDateTime createdAt,
        String idAutor,
        UUID idUser
) {
    public BookResponseDTO(Book book) {
        this(
                book.getId(),
                book.getTitle(),
                book.getGenre(),
                book.getSinopse(),
                book.getUrlPdf(),
                book.getPublicationDate(),
                book.getCreatedAt(),
                book.getIdAutor(),
                book.getUser() != null ? book.getUser().getId() : null
        );
    }
}
