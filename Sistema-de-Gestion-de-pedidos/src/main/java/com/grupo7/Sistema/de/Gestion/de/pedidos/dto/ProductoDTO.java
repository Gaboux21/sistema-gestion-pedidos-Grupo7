package com.grupo7.Sistema.de.Gestion.de.pedidos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductoDTO {

    @NotNull
    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(max = 100, message = "El nombre no debe exceder los 100 caracteres.")
    private String nombre;

    @Min(value = 0, message = "El stock no puede ser negativo.")
    private Integer stock;

    @NotNull(message = "El precio no puede ser nulo.")
    @Min(value = 1, message = "El precio debe ser mayor a cero.")
    private Double precio;

    @Size(max = 500, message = "La descripción no debe exceder los 500 caracteres.")
    private String descripcion;

    // Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}