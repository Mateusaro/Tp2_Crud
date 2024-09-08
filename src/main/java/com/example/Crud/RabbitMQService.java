package com.example.Crud;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQService {

    private final AmqpTemplate amqpTemplate;

    // Injeta o nome da fila a partir do application.properties
    @Value("${spring.rabbitmq.queue}")
    private String queueName;

    public RabbitMQService(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    // Método para enviar mensagem para a fila
    public void sendMessage(String message) {
        amqpTemplate.convertAndSend(queueName, message);
        System.out.println("Mensagem enviada: " + message);
    }

    // Listener para consumir mensagens da fila
    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receiveMessage(String message) {
        System.out.println("Mensagem recebida: " + message);
    }
}
