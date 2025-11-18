package com.example.concessionaria.service;

import com.example.concessionaria.model.Automovel;
import com.example.concessionaria.repository.AutomovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service contém a lógica de negócio da aplicação
 * Camada intermediária entre Controller e Repository
 */
@Service
public class AutomovelService {

    @Autowired
    private AutomovelRepository automovelRepository;

    public List<Automovel> listarTodos() {
        return automovelRepository.findAll();
    }

    public Optional<Automovel> buscarPorId(Long id) {
        return automovelRepository.findById(id);
    }

    @Transactional
    public Automovel salvar(Automovel automovel) {
        return automovelRepository.save(automovel);
    }

    @Transactional
    public void deletar(Long id) {
        automovelRepository.deleteById(id);
    }

    public List<Automovel> listarDisponiveis() {
        return automovelRepository.findByDisponivelTrue();
    }

    public List<Automovel> listarIndisponiveis() {
        return automovelRepository.findByDisponivelFalse();
    }

    public List<Automovel> buscarPorMarca(String marca) {
        return automovelRepository.findByMarcaContainingIgnoreCase(marca);
    }

    public List<Automovel> buscarPorModelo(String modelo) {
        return automovelRepository.findByModeloContainingIgnoreCase(modelo);
    }

    public List<Automovel> buscarPorAno(int ano) {
        return automovelRepository.findByAno(ano);
    }

    public List<Automovel> buscarPorFaixaDePreco(double precoMin, double precoMax) {
        return automovelRepository.findByPrecoBetween(precoMin, precoMax);
    }

    public Automovel buscarPorPlaca(String placa) {
        return automovelRepository.findByPlaca(placa);
    }

    public List<Automovel> buscarPorCor(String cor) {
        return automovelRepository.findByCorContainingIgnoreCase(cor);
    }

    @Transactional
    public Automovel marcarComoVendido(Long id) {
        Optional<Automovel> automovelOpt = automovelRepository.findById(id);
        if (automovelOpt.isPresent()) {
            Automovel automovel = automovelOpt.get();
            automovel.setDisponivel(false);
            return automovelRepository.save(automovel);
        }
        return null;
    }

    @Transactional
    public Automovel marcarComoDisponivel(Long id) {
        Optional<Automovel> automovelOpt = automovelRepository.findById(id);
        if (automovelOpt.isPresent()) {
            Automovel automovel = automovelOpt.get();
            automovel.setDisponivel(true);
            return automovelRepository.save(automovel);
        }
        return null;
    }

    @Transactional
    public Automovel atualizarPreco(Long id, double novoPreco) {
        Optional<Automovel> automovelOpt = automovelRepository.findById(id);
        if (automovelOpt.isPresent()) {
            Automovel automovel = automovelOpt.get();
            automovel.setPreco(novoPreco);
            return automovelRepository.save(automovel);
        }
        return null;
    }
}