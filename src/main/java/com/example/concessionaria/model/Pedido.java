package com.example.concessionaria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "pedidos")
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

    private LocalDateTime dataPedido;
}
