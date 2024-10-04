package com.example.Crud.feign;

import com.example.Crud.model.CinemaHistorico;
import com.example.Crud.model.Cinema;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "cinema-service", url = "http://cinema-service:8082")
public interface CinemaClient {

    @GetMapping("/cinemas")
    List<Cinema> getCinemas();

    @GetMapping("/cinemas/historico")
    List<CinemaHistorico> getHistorico();  // Novo método para histórico

    @PostMapping("/cinemas")
    Cinema createCinema(@RequestBody Cinema cinema);

    @PutMapping("/cinemas/{id}")
    Cinema updateCinema(@PathVariable("id") Long id, @RequestBody Cinema cinema);

    @DeleteMapping("/cinemas/delete/{id}")
    void deleteCinema(@PathVariable("id") Long id);
}
