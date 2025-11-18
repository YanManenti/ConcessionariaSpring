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
    @JoinColumn(name = "vendedor_id", referencedColumnName = "id")
    private User vendedor;

    private LocalDateTime dataInicio;

    private LocalDateTime dataCompra;
}
