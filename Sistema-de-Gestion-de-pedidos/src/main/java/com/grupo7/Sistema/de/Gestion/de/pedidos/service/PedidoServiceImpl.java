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
import com.grupo7.Sistema.de.Gestion.de.pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoProducer pedidoProducer; // Agregamos el productor

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Pedido crearPedido(Long usuarioId, List<DetallePedidoDTO> detallesDTO) {
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
            detalles.add(detalle);

            total += detalle.getSubtotal();
        }

        pedido.setDetalles(detalles);
        pedido.setTotal(total);
        Pedido nuevoPedido = pedidoRepository.save(pedido);
        pedidoProducer.enviarCreacionPedido("Pedido creado con ID: " + nuevoPedido.getId());
        return nuevoPedido;
    }

    @Override
    public Pedido modificarPedido(Long pedidoId, List<DetallePedidoDTO> detallesDTO) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + pedidoId));

        for (DetallePedido detalle : pedido.getDetalles()) {
            Producto producto = detalle.getProducto();
            producto.setStock(producto.getStock() + detalle.getCantidad());
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
            nuevosDetalles.add(nuevoDetalle);

            nuevoTotal += nuevoDetalle.getSubtotal();
        }

        pedido.setDetalles(nuevosDetalles);
        pedido.setTotal(nuevoTotal);

        Pedido pedidoModificado = pedidoRepository.save(pedido);
        pedidoProducer.enviarModificacionPedido("Pedido modificado con ID: " + pedidoModificado.getId());
        return pedidoModificado;
    }

    @Override
    public void cancelarPedido(Long pedidoId) {
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
    }

    @Override
    public Pedido obtenerPedidoPorId(Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + pedidoId));
    }

    @Override
    public List<Pedido> listarPedidosPorUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioId));
        return pedidoRepository.findByUsuario(usuario);
    }
}