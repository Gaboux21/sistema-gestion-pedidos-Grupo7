package com.grupo7.Sistema.de.Gestion.de.pedidos.service;

import com.grupo7.Sistema.de.Gestion.de.pedidos.dto.ProductoDTO;
import com.grupo7.Sistema.de.Gestion.de.pedidos.model.Producto;
import com.grupo7.Sistema.de.Gestion.de.pedidos.repository.ProductoRepository;
import com.grupo7.Sistema.de.Gestion.de.pedidos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto crearProducto(ProductoDTO productoDTO) {
        if (productoDTO.getNombre() == null || productoDTO.getNombre().trim().isEmpty()) {
            throw new RuntimeException("El nombre del producto no puede estar vacío.");
        }

        if (productoRepository.existsByNombre(productoDTO.getNombre())) {
            throw new RuntimeException("El nombre del producto ya existe.");
        }
        if (productoDTO.getPrecio() <= 0) {
            throw new RuntimeException("El precio debe ser mayor a cero.");
        }
        if (productoDTO.getStock() < 0) {
            throw new RuntimeException("El stock no puede ser negativo.");
        }

        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setStock(productoDTO.getStock());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setDescripcion(productoDTO.getDescripcion());
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    @Override
    public Producto actualizarProducto(Long id, ProductoDTO productoDTO) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        if (productoDTO.getPrecio() <= 0) {
            throw new RuntimeException("El precio debe ser mayor a cero.");
        }
        if (productoDTO.getStock() < 0) {
            throw new RuntimeException("El stock no puede ser negativo.");
        }

        producto.setNombre(productoDTO.getNombre());
        producto.setStock(productoDTO.getStock());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setDescripcion(productoDTO.getDescripcion());
        return productoRepository.save(producto);
    }

    @Override
    public void eliminarProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("No existe un producto con el ID especificado.");
        }
        productoRepository.deleteById(id);
    }
}