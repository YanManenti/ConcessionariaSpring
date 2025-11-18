package com.example.concessionaria.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private User cliente;
    @OneToOne
    @JoinColumn(name = "automovel_id", referencedColumnName = "id")
    private Automovel automovel;
    private LocalDateTime dataInicio;

}
