package com.example.concessionaria.controller;

import com.example.concessionaria.model.Automovel;
import com.example.concessionaria.service.AutomovelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/automoveis")
@CrossOrigin(origins = "*")
public class AutomovelController {

    @Autowired
    private AutomovelService automovelService;

    @GetMapping
    public ResponseEntity<List<Automovel>> listarTodos() {
        List<Automovel> automoveis = automovelService.listarTodos();
        return ResponseEntity.ok(automoveis);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Automovel> buscarPorId(@PathVariable Long id) {
        Optional<Automovel> automovel = automovelService.buscarPorId(id);
        return automovel.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Automovel> criar(@Valid @RequestBody Automovel automovel) {
        Automovel novoAutomovel = automovelService.salvar(automovel);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAutomovel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Automovel> atualizar(@PathVariable Long id, @Valid @RequestBody Automovel automovel) {
        Optional<Automovel> automovelExistente = automovelService.buscarPorId(id);

        if (!automovelExistente.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        automovel.setId(id);
        Automovel automovelAtualizado = automovelService.salvar(automovel);
        return ResponseEntity.ok(automovelAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Optional<Automovel> automovel = automovelService.buscarPorId(id);

        if (!automovel.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        automovelService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<Automovel>> listarDisponiveis() {
        List<Automovel> automoveis = automovelService.listarDisponiveis();
        return ResponseEntity.ok(automoveis);
    }

    @GetMapping("/indisponiveis")
    public ResponseEntity<List<Automovel>> listarIndisponiveis() {
        List<Automovel> automoveis = automovelService.listarIndisponiveis();
        return ResponseEntity.ok(automoveis);
    }

    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<Automovel>> buscarPorMarca(@PathVariable String marca) {
        List<Automovel> automoveis = automovelService.buscarPorMarca(marca);
        return ResponseEntity.ok(automoveis);
    }

    @GetMapping("/modelo/{modelo}")
    public ResponseEntity<List<Automovel>> buscarPorModelo(@PathVariable String modelo) {
        List<Automovel> automoveis = automovelService.buscarPorModelo(modelo);
        return ResponseEntity.ok(automoveis);
    }

    @GetMapping("/ano/{ano}")
    public ResponseEntity<List<Automovel>> buscarPorAno(@PathVariable int ano) {
        List<Automovel> automoveis = automovelService.buscarPorAno(ano);
        return ResponseEntity.ok(automoveis);
    }

    @GetMapping("/preco")
    public ResponseEntity<List<Automovel>> buscarPorFaixaDePreco(
            @RequestParam double min,
            @RequestParam double max) {
        List<Automovel> automoveis = automovelService.buscarPorFaixaDePreco(min, max);
        return ResponseEntity.ok(automoveis);
    }

    @GetMapping("/placa/{placa}")
    public ResponseEntity<Automovel> buscarPorPlaca(@PathVariable String placa) {
        Automovel automovel = automovelService.buscarPorPlaca(placa);

        if (automovel == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(automovel);
    }

    @GetMapping("/cor/{cor}")
    public ResponseEntity<List<Automovel>> buscarPorCor(@PathVariable String cor) {
        List<Automovel> automoveis = automovelService.buscarPorCor(cor);
        return ResponseEntity.ok(automoveis);
    }

    @PatchMapping("/{id}/marcar-vendido")
    public ResponseEntity<Automovel> marcarComoVendido(@PathVariable Long id) {
        Automovel automovel = automovelService.marcarComoVendido(id);

        if (automovel == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(automovel);
    }

    @PatchMapping("/{id}/marcar-disponivel")
    public ResponseEntity<Automovel> marcarComoDisponivel(@PathVariable Long id) {
        Automovel automovel = automovelService.marcarComoDisponivel(id);

        if (automovel == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(automovel);
    }

    @PatchMapping("/{id}/preco")
    public ResponseEntity<Automovel> atualizarPreco(
            @PathVariable Long id,
            @RequestParam double novoPreco) {
        Automovel automovel = automovelService.atualizarPreco(id, novoPreco);

        if (automovel == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(automovel);
    }
}