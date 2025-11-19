package com.example.concessionaria.dto.request;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record PatchRoleRequestDTO(
    @Size(min = 3, max = 20)
    String name,
    @Positive
    Double salario
) {
}
