package com.example.Crud.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue cinemaQueue() {
        return new Queue("cinemaQueue", true);
    }

    @Bean
    public Queue filmeQueue() {
        return new Queue("filmeQueue", true);
    }

    @Bean
    public MessageConverter jacksonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter jacksonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jacksonMessageConverter);
        return rabbitTemplate;
    }

    @Bean
    public SimpleMessageListenerContainer cinemaListenerContainer(ConnectionFactory connectionFactory, MessageConverter jacksonMessageConverter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames("cinemaQueue");

        // Configura o MessageListenerAdapter
        MessageListenerAdapter adapter = new MessageListenerAdapter(new RabbitService(rabbitTemplate(connectionFactory, jacksonMessageConverter)), "receiveCinemaMessage");
        adapter.setMessageConverter(jacksonMessageConverter);
        container.setMessageListener(adapter);

        return container;
    }

    @Bean
    public SimpleMessageListenerContainer filmeListenerContainer(ConnectionFactory connectionFactory, MessageConverter jacksonMessageConverter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames("filmeQueue");

        // Configura o MessageListenerAdapter
        MessageListenerAdapter adapter = new MessageListenerAdapter(new RabbitService(rabbitTemplate(connectionFactory, jacksonMessageConverter)), "receiveFilmeMessage");
        adapter.setMessageConverter(jacksonMessageConverter);
        container.setMessageListener(adapter);

        return container;
    }
}
