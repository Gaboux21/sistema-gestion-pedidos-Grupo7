package com.grupo7.Sistema.de.Gestion.de.pedidos.application.service;

import com.grupo7.Sistema.de.Gestion.de.pedidos.application.dto.PedidoDTO;
import com.grupo7.Sistema.de.Gestion.de.pedidos.domain.model.Pedido;
import com.grupo7.Sistema.de.Gestion.de.pedidos.domain.model.Usuario;
import com.grupo7.Sistema.de.Gestion.de.pedidos.domain.repository.PedidoRepository;
import com.grupo7.Sistema.de.Gestion.de.pedidos.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService{

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Pedido crearPedido(PedidoDTO pedidoDTO) {
        Usuario usuario = usuarioRepository.findById(pedidoDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Pedido pedido = new Pedido();
        pedido.setProducto(pedidoDTO.getProducto());
        pedido.setCantidad(pedidoDTO.getCantidad());
        pedido.setPrecioTotal(pedidoDTO.getPrecioTotal());
        pedido.setUsuario(usuario);

        return pedidoRepository.save(pedido);
    }

    @Override
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

}
