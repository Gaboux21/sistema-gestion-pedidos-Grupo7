package com.grupo7.Sistema.de.Gestion.de.pedidos.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles", uniqueConstraints = {
        @UniqueConstraint(columnNames = "nombre")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del rol no puede estar vacío")
    @Size(max = 50, message = "El nombre del rol no puede superar los 50 caracteres")
    @Column(nullable = false, unique = true, length = 50)
    private String nombre;


    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
    @Column(nullable = false, length = 255)
    private String descripcion;
    }

