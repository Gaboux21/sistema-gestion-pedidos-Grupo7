package com.grupo7.Sistema.de.Gestion.de.pedidos.controller;

import com.grupo7.Sistema.de.Gestion.de.pedidos.dto.DetallePedidoDTO;
import com.grupo7.Sistema.de.Gestion.de.pedidos.model.Pedido;
import com.grupo7.Sistema.de.Gestion.de.pedidos.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*@Tag(name = "Pedidos", description = "Gestion de pedidos")*/
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    /*@Operation(summary = "Crear un nuevo pedido", description = "Permite registrar un nuevo pedido.")*/
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestParam Long usuarioId, @RequestBody List<DetallePedidoDTO> detallesDTO) {
        Pedido pedido = pedidoService.crearPedido(usuarioId, detallesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> modificarPedido(@PathVariable Long id, @RequestBody List<DetallePedidoDTO> detallesDTO) {
        Pedido pedidoModificado = pedidoService.modificarPedido(id, detallesDTO);
        return ResponseEntity.ok(pedidoModificado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarPedido(@PathVariable Long id) {
        pedidoService.cancelarPedido(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedido(@PathVariable Long id) {
        Pedido pedido = pedidoService.obtenerPedidoPorId(id);
        return ResponseEntity.ok(pedido);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidosPorUsuario(@RequestParam Long usuarioId) {
        return ResponseEntity.ok(pedidoService.listarPedidosPorUsuario(usuarioId));
    }
}