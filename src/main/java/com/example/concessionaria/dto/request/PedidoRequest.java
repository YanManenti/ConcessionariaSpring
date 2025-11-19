package com.example.concessionaria.dto.request;

import jakarta.validation.constraints.NotNull;

public record PedidoRequest(

        @NotNull(message = "O ID do cliente é obrigatório")
        Long clienteId,

        @NotNull(message = "O ID do automóvel é obrigatório")
        Long automovelId

) {}