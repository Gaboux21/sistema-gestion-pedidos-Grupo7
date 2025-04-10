package com.grupo7.Sistema.de.Gestion.de.pedidos.Kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PedidoProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.pedidos.creacion}")
    private String topicoCreacion;

    @Value("${kafka.topic.pedidos.modificacion}")
    private String topicoModificacion;

    @Value("${kafka.topic.pedidos.cancelacion}")
    private String topicoCancelacion;

    public PedidoProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarCreacionPedido(String mensaje) {
        kafkaTemplate.send(topicoCreacion, mensaje);
    }

    public void enviarModificacionPedido(String mensaje) {
        kafkaTemplate.send(topicoModificacion, mensaje);
    }

    public void enviarCancelacionPedido(String mensaje) {
        kafkaTemplate.send(topicoCancelacion, mensaje);
    }
}