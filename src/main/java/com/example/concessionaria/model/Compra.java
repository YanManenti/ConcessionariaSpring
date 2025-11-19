package com.example.concessionaria.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private User cliente;

    @OneToOne
    @JoinColumn(name = "automovel_id", referencedColumnName = "id")
    private Automovel automovel;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", referencedColumnName = "id")
    private User funcionario;

    private LocalDateTime dataInicio;

    private LocalDateTime dataCompra;
}
