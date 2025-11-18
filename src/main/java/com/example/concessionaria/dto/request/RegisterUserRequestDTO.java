package com.example.concessionaria.dto.request;

import jakarta.validation.constraints.*;

public record RegisterUserRequestDTO(
        @Max(value = 50, message = "Nome deve ter no máximo 50 caracteres")
        @Min(value = 3, message = "Nome deve ter no mínimo 3 caracteres")
        @NotBlank(message = "Nome não pode ser vazio")
        String name,

        @Max(value = 15, message = "Telefone deve ter no máximo 15 caracteres")
        @Min(value = 10, message = "Telefone deve ter no mínimo 10 caracteres")
        String telefone,

        @NotBlank(message = "Email não pode ser vazio")
        @Email(message = "Email deve ser válido")
        String email,

        @Size(max = 50)
        String endereco,

        @Max(value = 50, message = "Senha deve ter no máximo 50 caracteres")
        @Min(value = 8, message = "Senha deve ter no mínimo 8 caracteres")
        @NotBlank(message = "Senha não pode ser vazia")
        String password,

        String role
) {
}
