package com.example.concessionaria.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public enum Roles {
    ADMIN,
    DIRETOR,
    VENDEDOR,
    CLIENTE;

    @NotBlank(message = "Email não pode ser vazio")
    @Email(message = "Email deve ser válido")
    public String toLowerCase() {
        return name().toLowerCase();
    }
}
