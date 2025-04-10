package com.grupo7.Sistema.de.Gestion.de.pedidos.controller;

import com.grupo7.Sistema.de.Gestion.de.pedidos.dto.ProductoDTO;
import com.grupo7.Sistema.de.Gestion.de.pedidos.model.Producto;
import com.grupo7.Sistema.de.Gestion.de.pedidos.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*@Tag(name = "Productos", description = "Gestion de pedidos")*/
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    /*@Operation(summary = "Crear un nuevo producto", description = "Permite registrar un nuevo producto.")*/
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody ProductoDTO productoDTO) {
        Producto nuevoProducto = productoService.crearProducto(productoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    /*@Operation(summary = "Obtener todos los productos", description = "Permite visualizar la lista de productos.")*/
    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok(productoService.listarProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerProductoPorId(id));
    }

    /*@Operation(summary = "Modifica un producto existente", description = "Permite modificar un producto existente")*/
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        Producto productoActualizado = productoService.actualizarProducto(id, productoDTO);
        return ResponseEntity.ok(productoActualizado);
    }

    /*@Operation(summary = "Elimina un producto existente", description = "Permite eliminar un producto existente.")*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}