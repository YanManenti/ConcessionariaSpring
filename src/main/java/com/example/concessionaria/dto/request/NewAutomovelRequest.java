package com.example.concessionaria.dto.request;

import com.example.concessionaria.model.Automovel;
import com.example.concessionaria.validator.UniqueValue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record NewAutomovelRequest(
        @Size(min=3, max=50, message = "O nome deve ter entre 3 e 50 caracteres")
        @NotNull(message = "O nome não pode ser nulo")
        String nome,
        @Size(min=3, max=50, message = "O modelo deve ter entre 3 e 50 caracteres")
        @NotNull(message = "O modelo não pode ser nulo")
        String modelo,
        @Size(min=3, max=50, message="A marca deve ter entre 3 e 50 caracteres")
        @NotNull(message="A marca não pode ser nula")
        String marca,
        @Positive(message = "O ano deve ser um valor positivo")
        @NotNull(message = "O ano não pode ser nulo")
        int ano,
        @Size(min=3, max=30, message="A cor deve ter entre 3 e 30 caracteres")
        @NotNull(message="A cor não pode ser nula")
        String cor,
        @Size(min=3, max=10, message="A placa deve ter entre 3 e 10 caracteres")
        @NotNull(message="A placa não pode ser nula")
        @UniqueValue(message = "A placa deve ser única", fieldName = "placa", domainClass = Automovel.class)
        String placa,
        @Positive(message = "O preço deve ser um valor positivo")
        @NotNull(message = "O preço não pode ser nulo")
        double preco,
        boolean disponivel
) {

}
