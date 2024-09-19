package com.example.Crud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cinema-service", url = "http://cinema-service:8082")
public interface CinemaClient {

    @GetMapping("/cinemas/{id}")
    Cinema getCinemaById(@PathVariable("id") Long id);

    @PostMapping("/cinemas")
    Cinema createCinema(@RequestBody Cinema cinema);

    @PostMapping("/cinemas/{id}")
    Cinema updateCinema(@PathVariable("id") Long id, @RequestBody Cinema cinema);

    @PostMapping("/cinemas/{id}/delete")
    void deleteCinema(@PathVariable("id") Long id);
}
