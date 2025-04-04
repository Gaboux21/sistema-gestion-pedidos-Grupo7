package com.grupo7.Sistema.de.Gestion.de.pedidos.infrastructure.controller;

import com.grupo7.Sistema.de.Gestion.de.pedidos.application.dto.PedidoDTO;
import com.grupo7.Sistema.de.Gestion.de.pedidos.domain.model.Pedido;
import com.grupo7.Sistema.de.Gestion.de.pedidos.application.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody PedidoDTO pedidoDTO) {
        return new ResponseEntity<>(pedidoService.crearPedido(pedidoDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }
}

