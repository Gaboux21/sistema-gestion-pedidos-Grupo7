package com.grupo7.Sistema.de.Gestion.de.pedidos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class DetallePedidoDTO {

    @NotNull(message = "El ID del producto no puede ser nulo.")
    private Long productoId;

    @NotNull(message = "La cantidad no puede ser nula.")
    @Min(value = 1, message = "La cantidad debe ser al menos 1.")
    private Integer cantidad;

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}