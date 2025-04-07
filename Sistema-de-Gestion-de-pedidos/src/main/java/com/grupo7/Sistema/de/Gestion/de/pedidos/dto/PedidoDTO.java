package com.grupo7.Sistema.de.Gestion.de.pedidos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class PedidoDTO {

    @NotNull(message = "El ID del usuario no puede ser nulo.")
    private Long usuarioId; // ID del usuario que realiza el pedido

    @NotEmpty(message = "El pedido debe contener al menos un producto.")
    private List<DetallePedidoDTO> detalles; // Lista de productos y cantidades

    // Getters y setters

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<DetallePedidoDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedidoDTO> detalles) {
        this.detalles = detalles;
    }
}