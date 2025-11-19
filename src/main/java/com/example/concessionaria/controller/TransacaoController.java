package com.example.concessionaria.controller;

import com.example.concessionaria.dto.request.CompraRequest;
import com.example.concessionaria.dto.request.PedidoRequest;
import com.example.concessionaria.model.Compra;
import com.example.concessionaria.model.Pedido;
import com.example.concessionaria.service.TransacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/api/transacao")
@RequiredArgsConstructor
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping("/clientes/pedido")
    public ResponseEntity<Pedido> criarPedido(@RequestBody @Valid PedidoRequest request) {
        Pedido novoPedido = transacaoService.registrarPedido(request);
        return ResponseEntity.ok(novoPedido);
    }

    @PostMapping("/vendedores/venda")
    public ResponseEntity<Compra> realizarVenda(@RequestBody @Valid CompraRequest request) {
        Compra novaCompra = transacaoService.realizarVenda(request);
        return ResponseEntity.ok(novaCompra);
    }
}