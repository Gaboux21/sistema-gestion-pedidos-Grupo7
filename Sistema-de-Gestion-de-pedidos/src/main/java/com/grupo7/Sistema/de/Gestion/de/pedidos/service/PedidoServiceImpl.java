package com.grupo7.Sistema.de.Gestion.de.pedidos.service;

import com.grupo7.Sistema.de.Gestion.de.pedidos.dto.PedidoDTO;
import com.grupo7.Sistema.de.Gestion.de.pedidos.model.Pedido;
import com.grupo7.Sistema.de.Gestion.de.pedidos.model.Usuario;
import com.grupo7.Sistema.de.Gestion.de.pedidos.repository.PedidoRepository;
import com.grupo7.Sistema.de.Gestion.de.pedidos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService{

    @Autowired
    private PedidoRepository pedidoRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Override
    public Pedido crearPedido(PedidoDTO pedidoDTO) {
        Usuario usuario = usuarioRepo.findById(pedidoDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Pedido pedido = new Pedido();
        pedido.setProducto(pedidoDTO.getProducto());
        pedido.setCantidad(pedidoDTO.getCantidad());
        pedido.setPrecioTotal(pedidoDTO.getPrecioTotal());
        pedido.setUsuario(usuario);

        return pedidoRepo.save(pedido);
    }

    @Override
    public List<Pedido> listarPedidos() {
        return pedidoRepo.findAll();
    }

}
