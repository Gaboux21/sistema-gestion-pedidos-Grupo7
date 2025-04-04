package com.grupo7.Sistema.de.Gestion.de.pedidos.repository;

import com.grupo7.Sistema.de.Gestion.de.pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
