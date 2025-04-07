package com.grupo7.Sistema.de.Gestion.de.pedidos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "producto", uniqueConstraints = {
        @UniqueConstraint(columnNames = "nombre") // Validación para que el nombre sea único
})
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no debe exceder 100 caracteres")
    private String nombre;

    @Column(nullable = false)
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @Column(nullable = false)
    @NotNull(message = "El precio no puede ser nulo")
    @Min(value = 1, message = "El precio debe ser mayor a cero")
    private Double precio;

    @Column
    @Size(max = 500, message = "La descripción no debe exceder 500 caracteres")
    private String descripcion;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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