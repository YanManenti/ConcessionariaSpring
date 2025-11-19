package com.example.concessionaria.service;

import com.example.concessionaria.model.Pedido;
import com.example.concessionaria.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UserService userService;

    public List<Pedido> listarPorUsuario(Long id){
        return pedidoRepository.findAll().stream()
                .filter(pedido -> pedido.getCliente().getId().equals(id))
                .toList();
    }

    public Pedido criarPedido(Long usuarioId, Long automovelId){
        Pedido novoPedido = new Pedido();
         novoPedido.setCliente(userService.getUserById(usuarioId));
//         novoPedido.setAutomovel(automovelService.getAutomovelById(automovelId));
         novoPedido.setDataInicio(LocalDateTime.now());
        return pedidoRepository.save(novoPedido);
    }
}
