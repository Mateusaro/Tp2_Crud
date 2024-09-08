package com.example.Crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    @Autowired
    private RabbitMQService rabbitMQService;  // Agora usamos RabbitMQService que combina as funções de envio e recebimento de mensagens

    @Autowired
    private CinemaService cinemaService;

    @PostMapping
    public Cinema createCinema(@RequestBody Cinema cinema, @RequestParam String cep) {
        Cinema createdCinema = cinemaService.createCinema(cinema, cep);
        // Enviar uma mensagem para o RabbitMQ informando sobre o novo cinema
        rabbitMQService.sendMessage("Novo cinema criado: " + createdCinema.getNome() + ", CEP: " + cep);
        return createdCinema;
    }

    @GetMapping
    public List<Cinema> getAllCinemas() {
        return cinemaService.getAllCinemas();
    }

    @GetMapping("/{id}")
    public Cinema getCinemaById(@PathVariable Long id) {
        return cinemaService.getCinemaById(id);
    }
}
