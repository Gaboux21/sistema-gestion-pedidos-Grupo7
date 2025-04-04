package com.grupo7.Sistema.de.Gestion.de.pedidos.application.service;

import com.grupo7.Sistema.de.Gestion.de.pedidos.application.dto.PedidoDTO;
import com.grupo7.Sistema.de.Gestion.de.pedidos.domain.model.Pedido;

import java.util.List;

public interface PedidoService {
    Pedido crearPedido(PedidoDTO pedidoDTO);
    List<Pedido> listarPedidos();
}
