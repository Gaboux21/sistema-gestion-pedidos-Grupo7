package com.grupo7.Sistema.de.Gestion.de.pedidos.repository;

import com.grupo7.Sistema.de.Gestion.de.pedidos.model.Pedido;
import com.grupo7.Sistema.de.Gestion.de.pedidos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByUsuario(Usuario usuario);
}