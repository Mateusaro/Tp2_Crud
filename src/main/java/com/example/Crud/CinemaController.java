package com.example.Crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @PostMapping
    public Cinema createCinema(@RequestBody CinemaDTO cinemaDTO) {
        return cinemaService.createCinema(cinemaDTO.getNome(), cinemaDTO.getCep());
    }
    @GetMapping
    public List<CinemaHistorico> getHistorico() {
        return cinemaService.getHistorico();
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
