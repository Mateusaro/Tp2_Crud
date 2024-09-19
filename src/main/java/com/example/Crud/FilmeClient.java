package com.example.Crud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "filme-service", url = "http://filme-service:8083")
public interface FilmeClient {

    @GetMapping("/filmes/{id}")
    Filme getFilmeById(@PathVariable("id") Long id);

    @PostMapping("/filmes")
    Filme createFilme(@RequestBody Filme filme);

    @PutMapping("/filmes/{id}")
    Filme updateFilme(@PathVariable("id") Long id, @RequestBody Filme filme);

    @DeleteMapping("/filmes/delete/{id}")
    void deleteFilme(@PathVariable("id") Long id);
}
