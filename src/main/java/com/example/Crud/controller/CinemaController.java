package com.example.Crud.controller;

import com.example.Crud.dto.CinemaDTO;
import com.example.Crud.model.CinemaHistorico;
import com.example.Crud.service.CinemaService;
import com.example.Crud.model.Cinema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @GetMapping
    public List<Cinema> getCinemas() {
        return cinemaService.getCinemas();
    }

    @PostMapping
    public Cinema createCinema(@RequestBody CinemaDTO cinemaDTO) {
        return cinemaService.createCinema(cinemaDTO.getNome(), cinemaDTO.getCep());
    }
    @PutMapping("/{id}")
    public Cinema updateCinema(@PathVariable Long id, @RequestBody Cinema cinema) {
        return cinemaService.updateCinema(id, cinema);
    }

    @DeleteMapping("/{id}")
    public void deleteCinema(@PathVariable Long id) {
        cinemaService.deleteCinema(id);
    }
}
