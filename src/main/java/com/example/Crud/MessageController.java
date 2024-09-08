package com.example.Crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PostMapping
    public void sendMessage(@RequestBody String message) {
        rabbitMQService.sendMessage(message);
    }
}
