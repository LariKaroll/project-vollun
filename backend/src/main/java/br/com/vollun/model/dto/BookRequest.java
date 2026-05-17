package br.com.vollun.model.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public record BookRequest(
        String title,
        String sinopse,
        LocalDate publicationDate,
        UUID idAutor,
        String genre
) {
}
