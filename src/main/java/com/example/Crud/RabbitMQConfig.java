package com.example.Crud;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class RabbitMQConfig {

    // Injeta o nome da fila a partir do application.properties
    @Value("${spring.rabbitmq.queue}")
    private String queueName;

    // Cria uma fila no RabbitMQ
    @Bean
    public Queue queue() {
        return new Queue(queueName, true);
    }
}

