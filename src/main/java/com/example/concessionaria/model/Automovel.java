package com.example.concessionaria.model;

import jakarta.persistence.*;
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
    private String nome;
    private String modelo;
    private String marca;
    private int ano;
    private String cor;
    private String placa;
    private double preco;
    private boolean disponivel;
    @OneToOne(mappedBy = "automovel", cascade = CascadeType.ALL)
    private Compra compra;
}
