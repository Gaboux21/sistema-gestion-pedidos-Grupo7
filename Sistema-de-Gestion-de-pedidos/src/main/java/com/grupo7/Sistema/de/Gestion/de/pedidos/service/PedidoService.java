package com.grupo7.Sistema.de.Gestion.de.pedidos.service;

import com.grupo7.Sistema.de.Gestion.de.pedidos.dto.DetallePedidoDTO;
import com.grupo7.Sistema.de.Gestion.de.pedidos.model.Pedido;

import java.util.List;

public interface PedidoService {
    Pedido crearPedido(Long usuarioId, List<DetallePedidoDTO> detallesDTO);
    Pedido modificarPedido(Long pedidoId, List<DetallePedidoDTO> detallesDTO);
    void cancelarPedido(Long pedidoId);
    Pedido obtenerPedidoPorId(Long pedidoId);
    List<Pedido> listarPedidosPorUsuario(Long usuarioId);
}