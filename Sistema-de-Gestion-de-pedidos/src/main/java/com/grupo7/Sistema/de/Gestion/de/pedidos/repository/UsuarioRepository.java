package com.grupo7.Sistema.de.Gestion.de.pedidos.repository;

import com.grupo7.Sistema.de.Gestion.de.pedidos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
    boolean exiteCorreo (String email);
}
