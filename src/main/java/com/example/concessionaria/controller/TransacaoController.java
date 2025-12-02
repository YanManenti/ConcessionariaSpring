package com.example.concessionaria.controller;

import com.example.concessionaria.dto.request.CompraRequestDTO;
import com.example.concessionaria.dto.request.PedidoRequestDTO;
import com.example.concessionaria.dto.response.CompraResponseDTO;
import com.example.concessionaria.dto.response.PedidoResponseDTO;
import com.example.concessionaria.model.Compra;
import com.example.concessionaria.model.Pedido;
import com.example.concessionaria.service.TransacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transacao")
@RequiredArgsConstructor
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping("/users/clientes/pedido")
    public ResponseEntity<PedidoResponseDTO> criarPedido(@RequestBody @Valid PedidoRequestDTO request) {
        Pedido novoPedido = transacaoService.registrarPedido(request);
        PedidoResponseDTO response = new PedidoResponseDTO(
                novoPedido.getCliente().getId(),
                novoPedido.getAutomovel().getId()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/users/vendedores/venda")
    public ResponseEntity<CompraResponseDTO> realizarVenda(@RequestBody @Valid CompraRequestDTO request) {
        Compra novaCompra = transacaoService.realizarVenda(request);
        CompraResponseDTO response = new CompraResponseDTO(
                novaCompra.getCliente().getId(),
                novaCompra.getVendedor().getId(),
                novaCompra.getAutomovel().getId()
        );
        return ResponseEntity.ok(response);
    }
}