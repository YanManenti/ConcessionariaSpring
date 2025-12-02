package com.example.concessionaria.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Automovel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=3, max=50, message = "O nome deve ter entre 3 e 50 caracteres")
    @NotNull(message = "O nome não pode ser nulo")
    private String nome;

    @Size(min=3, max=50, message = "O modelo deve ter entre 3 e 50 caracteres")
    @NotNull(message = "O modelo não pode ser nulo")
    private String modelo;

    @Size(min=3, max=50, message="A marca deve ter entre 3 e 50 caracteres")
    @NotNull(message="A marca não pode ser nula")
    private String marca;

    @Positive(message = "O ano deve ser um valor positivo")
    @NotNull(message = "O ano não pode ser nulo")
    private int ano;

    @Size(min=3, max=30, message="A cor deve ter entre 3 e 30 caracteres")
    @NotNull(message="A cor não pode ser nula")
    private String cor;

    @Size(min=3, max=10, message="A placa deve ter entre 3 e 10 caracteres")
    @NotNull(message="A placa não pode ser nula")
    private String placa;

    @Positive(message = "O preço deve ser um valor positivo")
    @NotNull(message = "O preço não pode ser nulo")
    private double preco;

    private boolean disponivel;

    @OneToOne(mappedBy = "automovel", cascade = CascadeType.ALL)
    private Compra compra;
}
