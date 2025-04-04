package com.grupo7.Sistema.de.Gestion.de.pedidos.domain.repository;

import com.grupo7.Sistema.de.Gestion.de.pedidos.domain.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    static Optional<Rol> buscarPorNombre(String nombre) {
        return null;
    }
}
