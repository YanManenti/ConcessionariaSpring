package com.example.concessionaria.service;

import com.example.concessionaria.dto.request.CompraRequest;
import com.example.concessionaria.dto.request.PedidoRequest;
import com.example.concessionaria.model.Automovel;
import com.example.concessionaria.model.Compra;
import com.example.concessionaria.model.Pedido;
import com.example.concessionaria.model.User;
import com.example.concessionaria.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final PedidoRepository pedidoRepository;
    private final CompraRepository compraRepository;
    private final AutomovelRepository automovelRepository;
    private final UserRepository userRepository;

    @Transactional
    public Pedido registrarPedido(PedidoRequest request) {
        Automovel automovel = automovelRepository.findById(request.automovelId())
                .orElseThrow(() -> new RuntimeException("Automóvel não encontrado."));

        if (automovel.isDisponivel()) {
            throw new RuntimeException("Este veículo está disponível no estoque! Realize a venda direta (Compra) em vez de um Pedido.");
        }

        User cliente = userRepository.findById(request.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setAutomovel(automovel);
        pedido.setDataPedido(LocalDateTime.now());

        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Compra realizarVenda(CompraRequest request) {
        Automovel automovel = automovelRepository.findById(request.automovelId())
                .orElseThrow(() -> new RuntimeException("Automóvel não encontrado."));

        if (!automovel.isDisponivel()) {
            throw new RuntimeException("A venda não pode ser concluída. Este veículo não está disponível (já vendido ou reservado).");
        }

        User cliente = userRepository.findById(request.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        User vendedor = userRepository.findById(request.vendedorId())
                .orElseThrow(() -> new RuntimeException("Vendedor não encontrado."));

        Compra compra = new Compra();
        compra.setCliente(cliente);
        compra.setAutomovel(automovel);
        compra.setVendedor(vendedor);
        compra.setDataCompra(LocalDateTime.now());

        automovel.setDisponivel(false);
        automovelRepository.save(automovel);

        return compraRepository.save(compra);
    }
}