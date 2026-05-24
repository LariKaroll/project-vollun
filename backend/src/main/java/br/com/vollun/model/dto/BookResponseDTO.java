package br.com.vollun.model.dto;

import br.com.vollun.model.entity.Book;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record BookResponseDTO(
        UUID id,
        String title,
        String genre,
        String sinopse,
        LocalDate publicationDate,
        String idAutor
) {}
