package com.example.concessionaria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double salario;
    private String nivelAcesso;
    @OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL)
    private List<Funcionario> funcionarios;
}
