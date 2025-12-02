package com.example.concessionaria.controller;

import com.example.concessionaria.config.JWTUserData;
import com.example.concessionaria.dto.request.CompraRequest;
import com.example.concessionaria.dto.request.PatchRoleRequestDTO;
import com.example.concessionaria.dto.request.UpdateUserRequestDTO;
import com.example.concessionaria.dto.response.PatchRoleResponseDTO;
import com.example.concessionaria.dto.response.UpdateUserResponseDTO;
import com.example.concessionaria.model.*;
import com.example.concessionaria.repository.UserRepository;
import com.example.concessionaria.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Locale.filter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PedidoService pedidoService;
    private final RoleService roleService;
    private final TransacaoService transacaoService;
    private final AutomovelService automovelService;

    @GetMapping("/eu")
    public ResponseEntity<User> getMyProfile(Authentication authentication) {
        JWTUserData userData = (JWTUserData) authentication.getPrincipal();
        String email = userData.email();
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return ResponseEntity.ok(user);
    }

    @PutMapping("/eu")
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

    @GetMapping("/diretores/funcionarios")
    public ResponseEntity<List<User>> listarFuncionarios() {
        List<User> funcionarios = userService.getAllUsers().stream()
                .filter(user -> user.getRole().getName().equals(String.valueOf(Roles.VENDEDOR)))
                .toList();
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/diretores/clientes")
    public ResponseEntity<List<User>> listarClientes() {
        List<User> clientes = userService.getUsersByRoleName(String.valueOf(Roles.CLIENTE));
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/diretores/cargos")
    public ResponseEntity<List<String>> listarCargos() {
        List<String> cargos = roleService.findAllRoleNames();
        return ResponseEntity.ok(cargos);
    }

    @GetMapping("/diretores/vendas")
    public ResponseEntity<List<Compra>> listarVendas() {
        List<Compra> vendas = transacaoService.listarCompras();
        return ResponseEntity.ok(vendas);
    }

    @GetMapping("/diretores/vendas/{clienteId}")
    public ResponseEntity<List<Compra>> listarVendasPorCliente(@PathVariable Long clienteId) {
        List<Compra> vendas = transacaoService.listarComprasPorCliente(clienteId);
        return ResponseEntity.ok(vendas);
    }


    // Problemas com o ENUM Roles ao criar, deletar e editar cargos???
    @PatchMapping("/diretores/editar/cargo/{cargoId}")
    public ResponseEntity<PatchRoleResponseDTO> alterarCargo(@RequestParam Long cargoId, @RequestBody PatchRoleRequestDTO patchRoleRequestDTO) {
        Role currentRole = roleService.findById(cargoId).orElseThrow(() -> new RuntimeException("Role not found"));
        String newName = patchRoleRequestDTO.name();
        Double newSalario = patchRoleRequestDTO.salario();
        if(newName != null) currentRole.setName(newName);
        if(newSalario != null) currentRole.setSalario(newSalario);
        roleService.save(currentRole);
        return ResponseEntity.ok(new PatchRoleResponseDTO(
                currentRole.getName(),
                currentRole.getSalario()
        ));
    }

    @PostMapping("/diretores/criar/cargo")
    public ResponseEntity<PatchRoleResponseDTO> criarCargo(@RequestBody PatchRoleRequestDTO patchRoleRequestDTO) {
        Role newRole = new Role();
        newRole.setName(patchRoleRequestDTO.name());
        newRole.setSalario(patchRoleRequestDTO.salario());
        roleService.save(newRole);
        return ResponseEntity.ok(new PatchRoleResponseDTO(
                newRole.getName(),
                newRole.getSalario()
        ));
    }

    @DeleteMapping("/diretores/deletar/cargo/{cargoId}")
    public ResponseEntity<String> deletarCargo(@PathVariable Long cargoId) {
        roleService.deleteById(cargoId);
        return ResponseEntity.ok("Cargo deletado com sucesso.");
    }



}