package com.grupo7.Sistema.de.Gestion.de.pedidos.controller;

import com.grupo7.Sistema.de.Gestion.de.pedidos.dto.DetallePedidoDTO;
import com.grupo7.Sistema.de.Gestion.de.pedidos.model.Pedido;
import com.grupo7.Sistema.de.Gestion.de.pedidos.service.PedidoService;
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
    public ResponseEntity<String> crearPedido(@RequestParam Long usuarioId, @RequestBody List<DetallePedidoDTO> detallesDTO) {
        try {
            String mensaje = pedidoService.crearPedido(usuarioId, detallesDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(mensaje); // 201 Created
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage()); // 400 Bad Request
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificarPedido(@PathVariable Long id, @RequestBody List<DetallePedidoDTO> detallesDTO) {
        try {
            String mensaje = pedidoService.modificarPedido(id, detallesDTO);
            return ResponseEntity.ok(mensaje); // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso no encontrado: " + e.getMessage()); // 404 Not Found
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelarPedido(@PathVariable Long id) {
        try {
            String mensaje = pedidoService.cancelarPedido(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(mensaje); // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso no encontrado: " + e.getMessage()); // 404 Not Found
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> obtenerPedido(@PathVariable Long id) {
        try {
            pedidoService.obtenerPedidoPorId(id);
            return ResponseEntity.ok("Operación exitosa."); // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso no encontrado: " + e.getMessage()); // 404 Not Found
        }
    }

    @GetMapping
    public ResponseEntity<String> listarPedidosPorUsuario(@RequestParam Long usuarioId) {
        try {
            pedidoService.listarPedidosPorUsuario(usuarioId);
            return ResponseEntity.ok("Operación exitosa."); // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso no encontrado: " + e.getMessage()); // 404 Not Found
        }
    }
}