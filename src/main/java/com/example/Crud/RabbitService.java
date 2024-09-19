package com.example.Crud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitService {

    private final AmqpTemplate amqpTemplate;

    @Value("${spring.rabbitmq.queue.cinema}")
    private String cinemaQueueName;

    @Value("${spring.rabbitmq.queue.filme}")
    private String filmeQueueName;

    public RabbitService(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendCinemaMessage(Cinema cinema) {
        amqpTemplate.convertAndSend(cinemaQueueName, cinema);
        log.info("Mensagem de Cinema enviada:: " + cinema);
    }

    public void sendFilmeMessage(Filme filme) {
        amqpTemplate.convertAndSend(filmeQueueName, filme);
        log.info("Mensagem de Filme enviada:: " + filme);
    }

    @RabbitListener(queues = "#{@cinemaQueue.name}")
    public void receiveCinemaMessage(Cinema cinema) {
        log.info("Mensagem de Cinema recebida: " + cinema);
    }

    @RabbitListener(queues = "#{@filmeQueue.name}")
    public void receiveFilmeMessage(Filme filme) { log.info("Mensagem de Filme recebida: " + filme);}
}
