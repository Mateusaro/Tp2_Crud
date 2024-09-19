package com.example.Crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private RabbitService rabbitService;

    @PostMapping("/cinema")
    public void sendCinemaMessage(@RequestBody Cinema cinema) {
        rabbitService.sendCinemaMessage(cinema);
    }

    @PostMapping("/filme")
    public void sendFilmeMessage(@RequestBody Filme filme) {
        rabbitService.sendFilmeMessage(filme);
    }
}
