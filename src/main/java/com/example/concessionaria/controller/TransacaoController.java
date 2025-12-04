package com.example.concessionaria.controller;

import com.example.concessionaria.config.JWTUserData;
import com.example.concessionaria.dto.request.CompraRequestDTO;
import com.example.concessionaria.dto.request.PedidoRequestDTO;
import com.example.concessionaria.dto.response.CompraResponseDTO;
import com.example.concessionaria.dto.response.PedidoResponseDTO;
import com.example.concessionaria.model.Compra;
import com.example.concessionaria.model.Pedido;
import com.example.concessionaria.model.User;
import com.example.concessionaria.repository.UserRepository;
import com.example.concessionaria.service.TransacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transacao")
@RequiredArgsConstructor
public class TransacaoController {

    private final TransacaoService transacaoService;
    private final UserRepository userRepository;

    @PostMapping("/users/clientes/pedido")
    public ResponseEntity<PedidoResponseDTO> criarPedido(Authentication authentication, @RequestBody @Valid PedidoRequestDTO request) {
        JWTUserData userData = (JWTUserData) authentication.getPrincipal();
        String email = userData.email();
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Pedido novoPedido = transacaoService.registrarPedido(request);
        PedidoResponseDTO response = new PedidoResponseDTO(
                novoPedido.getCliente().getId(),
                novoPedido.getAutomovel().getId()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/users/vendedores/venda")
    public ResponseEntity<CompraResponseDTO> realizarVenda(Authentication authentication, @RequestBody @Valid CompraRequestDTO request) {
        JWTUserData userData = (JWTUserData) authentication.getPrincipal();
        String email = userData.email();
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Compra novaCompra = transacaoService.realizarVenda(user, request);
        CompraResponseDTO response = new CompraResponseDTO(
                novaCompra.getCliente().getId(),
                novaCompra.getVendedor().getId(),
                novaCompra.getAutomovel().getId()
        );
        return ResponseEntity.ok(response);
    }
}