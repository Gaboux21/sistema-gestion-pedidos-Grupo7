package com.grupo7.Sistema.de.Gestion.de.pedidos.service;

import com.grupo7.Sistema.de.Gestion.de.pedidos.Kafka.PedidoProducer;
import com.grupo7.Sistema.de.Gestion.de.pedidos.dto.DetallePedidoDTO;
import com.grupo7.Sistema.de.Gestion.de.pedidos.model.DetallePedido;
import com.grupo7.Sistema.de.Gestion.de.pedidos.model.Pedido;
import com.grupo7.Sistema.de.Gestion.de.pedidos.model.Producto;
import com.grupo7.Sistema.de.Gestion.de.pedidos.model.Usuario;
import com.grupo7.Sistema.de.Gestion.de.pedidos.repository.PedidoRepository;
import com.grupo7.Sistema.de.Gestion.de.pedidos.repository.ProductoRepository;
import com.grupo7.Sistema.de.Gestion.de.pedidos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoProducer pedidoProducer;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public String crearPedido(Long usuarioId, List<DetallePedidoDTO> detallesDTO) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioId));

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setEstado("CREADO");

        List<DetallePedido> detalles = new ArrayList<>();
        double total = 0;

        for (DetallePedidoDTO detalleDTO : detallesDTO) {
            Producto producto = productoRepository.findById(detalleDTO.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + detalleDTO.getProductoId()));

            if (producto.getStock() < detalleDTO.getCantidad()) {
                throw new RuntimeException("No hay suficiente stock para el producto: " + producto.getNombre());
            }

            producto.setStock(producto.getStock() - detalleDTO.getCantidad());
            productoRepository.save(producto);

            DetallePedido detalle = new DetallePedido();
            detalle.setProducto(producto);
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setSubtotal(producto.getPrecio() * detalleDTO.getCantidad());
            detalle.setPedido(pedido);
            detalles.add(detalle);

            total += detalle.getSubtotal();
        }

        pedido.setDetalles(detalles);
        pedido.setTotal(total);

        pedidoRepository.save(pedido);

        usuario.setTotalSpent(usuario.getTotalSpent() + total);
        usuarioRepository.save(usuario);

        pedidoProducer.enviarCreacionPedido("Pedido creado con ID: " + pedido.getId());

        return "Recurso creado satisfactoriamente.";
    }

    @Override
    public String modificarPedido(Long pedidoId, List<DetallePedidoDTO> detallesDTO) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + pedidoId));

        for (DetallePedido detalle : pedido.getDetalles()) {
            Producto producto = detalle.getProducto();
            producto.setStock(producto.getStock() + detalle.getCantidad());
            productoRepository.save(producto);
        }

        List<DetallePedido> nuevosDetalles = new ArrayList<>();
        double nuevoTotal = 0;

        for (DetallePedidoDTO detalleDTO : detallesDTO) {
            Producto producto = productoRepository.findById(detalleDTO.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + detalleDTO.getProductoId()));

            if (producto.getStock() < detalleDTO.getCantidad()) {
                throw new RuntimeException("No hay suficiente stock para el producto: " + producto.getNombre());
            }

            producto.setStock(producto.getStock() - detalleDTO.getCantidad());
            productoRepository.save(producto);

            DetallePedido nuevoDetalle = new DetallePedido();
            nuevoDetalle.setProducto(producto);
            nuevoDetalle.setCantidad(detalleDTO.getCantidad());
            nuevoDetalle.setSubtotal(producto.getPrecio() * detalleDTO.getCantidad());
            nuevoDetalle.setPedido(pedido);
            nuevosDetalles.add(nuevoDetalle);

            nuevoTotal += nuevoDetalle.getSubtotal();
        }

        pedido.setDetalles(nuevosDetalles);
        pedido.setTotal(nuevoTotal);

        pedidoRepository.save(pedido);

        pedidoProducer.enviarModificacionPedido("Pedido modificado con ID: " + pedido.getId());

        return "Operación exitosa.";
    }

    @Override
    public String cancelarPedido(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + pedidoId));

        for (DetallePedido detalle : pedido.getDetalles()) {
            Producto producto = detalle.getProducto();
            producto.setStock(producto.getStock() + detalle.getCantidad());
            productoRepository.save(producto);
        }

        pedido.setEstado("CANCELADO");
        pedidoRepository.save(pedido);

        pedidoProducer.enviarCancelacionPedido("Pedido cancelado con ID: " + pedidoId);

        return "Recurso eliminado.";
    }

    @Override
    public Pedido obtenerPedidoPorId(Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Recurso no encontrado."));
    }

    @Override
    public List<Pedido> listarPedidosPorUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Recurso no encontrado."));
        return pedidoRepository.findByUsuario(usuario);
    }
}