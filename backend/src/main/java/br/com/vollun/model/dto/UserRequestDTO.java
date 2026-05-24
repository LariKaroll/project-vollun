package br.com.vollun.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.checkerframework.checker.units.qual.N;

import java.util.UUID;

public record UserRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String name,

        @NotBlank(message = "O username é obrigatório")
        String username,

        @Email(message = "E-mail inválido")
        @NotBlank(message = "O e-mail é obrigatório")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Size(message = "A senha deve ter no mínimo 6 caracteres", min= 6)
        String password,

        @NotBlank(message = "O CPF é obrigatório")
        String cpf
) {
}