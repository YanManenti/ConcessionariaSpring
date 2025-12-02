package com.example.concessionaria.dto.response;

import jakarta.validation.constraints.NotNull;

public record CompraResponseDTO(

        @NotNull(message = "O ID do automóvel é obrigatório")
        Long automovelId,

        @NotNull(message = "O ID do cliente é obrigatório")
        Long clienteId,

        @NotNull(message = "O ID do vendedor é obrigatório")
        Long funcionarioId
) {
}
