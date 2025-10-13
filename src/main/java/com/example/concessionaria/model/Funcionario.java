package com.example.concessionaria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    @ManyToOne
    @JoinColumn(name = "cargo_id", referencedColumnName = "id")
    private Cargo cargo;
    private String endereco;
    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private List<Compra> vendas = new ArrayList<>();
}
