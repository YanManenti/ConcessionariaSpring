package com.example.concessionaria.dto.request;

import jakarta.validation.constraints.*;

public record RegisterUserRequestDTO(

        @Size(min = 3, max = 50, message = "Nome deve ter entre 3 e 50 caracteres")
        @NotBlank(message = "Nome não pode ser vazio")
        String name,

        @NotBlank(message = "Email não pode ser vazio")
        @Email(message = "Email deve ser válido")
        String email,

        @Size(min = 8, max = 50, message = "Senha deve ter entre 8 e 50 caracteres")
        @NotBlank(message = "Senha não pode ser vazia")
        String password,

        String role
) {
}
