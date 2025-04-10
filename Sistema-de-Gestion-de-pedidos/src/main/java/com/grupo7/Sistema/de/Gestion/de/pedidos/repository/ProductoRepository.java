package com.grupo7.Sistema.de.Gestion.de.pedidos.repository;

import com.grupo7.Sistema.de.Gestion.de.pedidos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    boolean existsByNombre(String nombre);
}
