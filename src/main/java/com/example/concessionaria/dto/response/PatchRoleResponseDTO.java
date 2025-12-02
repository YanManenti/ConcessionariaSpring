package com.example.concessionaria.dto.response;

import com.example.concessionaria.model.Roles;

public record PatchRoleResponseDTO(
    Roles name,
    Double salario
) {
}
