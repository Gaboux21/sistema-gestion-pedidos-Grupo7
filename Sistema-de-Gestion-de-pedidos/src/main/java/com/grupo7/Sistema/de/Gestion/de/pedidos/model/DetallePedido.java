package com.grupo7.Sistema.de.Gestion.de.pedidos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_pedidos")
public class DetallePedido {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "producto_id", nullable = false)
        private Producto producto;

        @Column(nullable = false)
        private Integer cantidad;

        @Column(nullable = false)
        private Double subtotal;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Producto getProducto() {
            return producto;
        }

        public void setProducto(Producto producto) {
            this.producto = producto;
        }

        public Integer getCantidad() {
            return cantidad;
        }

        public void setCantidad(Integer cantidad) {
            this.cantidad = cantidad;
        }

        public Double getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(Double subtotal) {
            this.subtotal = subtotal;
        }
}
