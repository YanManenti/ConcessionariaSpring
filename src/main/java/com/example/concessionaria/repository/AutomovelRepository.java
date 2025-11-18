package com.example.concessionaria.repository;

import com.example.concessionaria.model.Automovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutomovelRepository extends JpaRepository<Automovel, Long> {

    List<Automovel> findByDisponivelTrue();

    List<Automovel> findByDisponivelFalse();

    List<Automovel> findByMarcaContainingIgnoreCase(String marca);

    List<Automovel> findByModeloContainingIgnoreCase(String modelo);

    List<Automovel> findByAno(int ano);

    List<Automovel> findByPrecoBetween(double precoMin, double precoMax);

    Automovel findByPlaca(String placa);

    List<Automovel> findByCorContainingIgnoreCase(String cor);
}