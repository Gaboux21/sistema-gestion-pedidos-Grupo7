package com.grupo7.Sistema.de.Gestion.de.pedidos.service;

import com.grupo7.Sistema.de.Gestion.de.pedidos.dto.ProductoDTO;
import com.grupo7.Sistema.de.Gestion.de.pedidos.model.Producto;

import java.util.List;

public interface ProductoService {
    Producto crearProducto(ProductoDTO productoDTO);
    List<Producto> listarProductos();
    Producto obtenerProductoPorId(Long id);
    Producto actualizarProducto(Long id, ProductoDTO productoDTO);
    void eliminarProducto(Long id);
}