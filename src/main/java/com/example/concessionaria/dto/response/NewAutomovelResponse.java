package com.example.concessionaria.dto.response;

public record NewAutomovelResponse(String nome, String modelo, String marca, int ano, String cor, String placa, double preco, boolean disponivel) {
}
