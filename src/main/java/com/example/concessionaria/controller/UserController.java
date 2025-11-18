package com.example.concessionaria.controller;

import com.example.concessionaria.config.JWTUserData;
import com.example.concessionaria.dto.request.UpdateUserRequestDTO;
import com.example.concessionaria.dto.response.UpdateUserResponseDTO;
import com.example.concessionaria.model.Pedido;
import com.example.concessionaria.model.User;
import com.example.concessionaria.repository.UserRepository;
import com.example.concessionaria.service.PedidoService;
import com.example.concessionaria.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PedidoService pedidoService;
    private final CompraService compraService;

    @GetMapping("/me")
    public ResponseEntity<User> getMyProfile(Authentication authentication) {
        JWTUserData userData = (JWTUserData) authentication.getPrincipal();
        String email = userData.email();
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return ResponseEntity.ok(user);
    }

    @PutMapping("/me")
    public ResponseEntity<UpdateUserResponseDTO> updateMyProfile(Authentication authentication, @RequestBody UpdateUserRequestDTO updateUserRequestDTO) {
        JWTUserData userData = (JWTUserData) authentication.getPrincipal();
        String email = userData.email();
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        User updatedUser = new User();
        updatedUser.setId(user.getId());
        if(updateUserRequestDTO.name() != null) updatedUser.setName(updateUserRequestDTO.name());
        if(updateUserRequestDTO.email() != null) updatedUser.setEmail(updateUserRequestDTO.email());
        if(updateUserRequestDTO.endereco() != null) updatedUser.setEndereco(updateUserRequestDTO.endereco());
        if(updateUserRequestDTO.endereco() != null) updatedUser.setEndereco(updateUserRequestDTO.endereco());
        if(updateUserRequestDTO.telefone() != null) updatedUser.setTelefone(updateUserRequestDTO.telefone());
        if(updateUserRequestDTO.password() != null) updatedUser.setPassword(updateUserRequestDTO.password());
        updatedUser.setRole(user.getRole());

        userService.updateUser(user.getId(), updatedUser);
        UpdateUserResponseDTO response = new UpdateUserResponseDTO(
                updatedUser.getName(),
                updatedUser.getEmail(),
                "Atualização realizada com sucesso."
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/comprar/{automovelId}")
    public ResponseEntity<Pedido> comprarAutomovel(Authentication authentication, @PathVariable Long automovelId) {
        JWTUserData userData = (JWTUserData) authentication.getPrincipal();
        String email = userData.email();
        User comprador = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Compra novaCompra = compraService.comprar(comprador.getId(), automovelId);
        return ResponseEntity.ok(novaCompra);
    }

    @PostMapping("/pedir/{automovelId}")
    public ResponseEntity<Pedido> pedirAutomovel(Authentication authentication, @PathVariable Long automovelId) {
        JWTUserData userData = (JWTUserData) authentication.getPrincipal();
        String email = userData.email();
        User usuario = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Pedido novoPedido = pedidoService.criarPedido(usuario.getId(), automovelId);
        return ResponseEntity.ok(novoPedido);
    }

    @GetMapping("/meus_pedidos")
    public ResponseEntity<List<Pedido>> meusPedidos(Authentication authentication) {
        JWTUserData userData = (JWTUserData) authentication.getPrincipal();
        String email = userData.email();
        User usuario = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        List<Pedido> pedidos = pedidoService.listarPorUsuario(usuario.getId());
        return ResponseEntity.ok(pedidos);
    }
}