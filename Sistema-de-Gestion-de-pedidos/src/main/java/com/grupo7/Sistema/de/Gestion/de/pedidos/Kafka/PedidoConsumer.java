package com.grupo7.Sistema.de.Gestion.de.pedidos.Kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoConsumer {

    @KafkaListener(topics = "${kafka.topic.pedidos.creacion}", groupId = "grupo-notificaciones")
    public void procesarCreacionPedido(String mensaje) {
        System.out.println("Notificación recibida: " + mensaje);
    }

    @KafkaListener(topics = "${kafka.topic.pedidos.modificacion}", groupId = "grupo-notificaciones")
    public void procesarModificacionPedido(String mensaje) {
        System.out.println("Notificación recibida: " + mensaje);
    }

    @KafkaListener(topics = "${kafka.topic.pedidos.cancelacion}", groupId = "grupo-notificaciones")
    public void procesarCancelacionPedido(String mensaje) {
        System.out.println("Notificación recibida: " + mensaje);
    }
}