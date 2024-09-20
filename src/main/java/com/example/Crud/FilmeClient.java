package com.example.Crud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "filme-service", url = "http://filme-service:8083")
public interface FilmeClient {


    @PostMapping("/filmes")
    Filme createFilme(@RequestBody Filme filme);

    @PutMapping("/filmes/{id}")
    Filme updateFilme(@PathVariable("id") Long id, @RequestBody Filme filme);

    @DeleteMapping("/filmes/delete/{id}")
    void deleteFilme(@PathVariable("id") Long id);

    @GetMapping("/filmes/historico")
    List<FilmeHistorico> getFilmeHistorico();
}
