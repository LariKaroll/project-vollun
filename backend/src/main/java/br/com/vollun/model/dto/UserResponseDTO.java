package br.com.vollun.model.dto;

import java.util.List;
import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String name,
        String email,
        String username,
        List<BookResponseDTO> book
) {
}
