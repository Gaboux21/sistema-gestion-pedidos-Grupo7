package com.grupo7.Sistema.de.Gestion.de.pedidos.service;

import com.grupo7.Sistema.de.Gestion.de.pedidos.dto.PedidoDTO;
import com.grupo7.Sistema.de.Gestion.de.pedidos.model.Pedido;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PedidoService {
    Pedido crearPedido(PedidoDTO pedidoDTO);
    List<Pedido> listarPedidos();
}
