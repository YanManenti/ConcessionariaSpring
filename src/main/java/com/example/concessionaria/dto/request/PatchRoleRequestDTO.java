package com.example.concessionaria.dto.request;

import com.example.concessionaria.model.Roles;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record PatchRoleRequestDTO(
//    @Size(min = 3, max = 20)
//    Roles name,
    @Positive
    Double salario
) {
}
