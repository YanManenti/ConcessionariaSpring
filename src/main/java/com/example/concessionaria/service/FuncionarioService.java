package com.example.concessionaria.service;

import com.example.concessionaria.model.Funcionario;
import com.example.concessionaria.repository.FuncionarioRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public List<Funcionario> getAllFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Funcionario getFuncionarioById(Long id) {
        return funcionarioRepository.findById(id).orElse(null);
    }

    public Funcionario saveFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public void deleteFuncionario(Long id) {
        funcionarioRepository.deleteById(id);
    }

    public Funcionario updateFuncionario(Long id, Funcionario funcionarioDetails) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElse(null);
        if (funcionario != null) {
            funcionario.setNome(funcionarioDetails.getNome());
            funcionario.setCpf(funcionarioDetails.getCpf());
            funcionario.setEmail(funcionarioDetails.getEmail());
            funcionario.setTelefone(funcionarioDetails.getTelefone());
            funcionario.setCargo(funcionarioDetails.getCargo());
            funcionario.setEndereco(funcionarioDetails.getEndereco());
            funcionario.setSalario(funcionarioDetails.getSalario());
            return funcionarioRepository.save(funcionario);
        }
        return null;
    }

}
