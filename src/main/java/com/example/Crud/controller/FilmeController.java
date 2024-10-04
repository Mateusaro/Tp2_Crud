package com.example.Crud.controller;

import com.example.Crud.model.FilmeHistorico;
import com.example.Crud.service.FilmeService;
import com.example.Crud.model.Filme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @PostMapping
    public Filme createFilme(@RequestBody Filme filme) {
        return filmeService.createFilme(filme);
    }

    @GetMapping("/historico")
    public List<FilmeHistorico> getHistorico() {
        return filmeService.getHistorico();
    }

    @PutMapping("/{id}")
    public Filme updateFilme(@PathVariable Long id, @RequestBody Filme filme) {
        return filmeService.updateFilme(id, filme);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFilme(@PathVariable Long id) {
        filmeService.deleteFilme(id);
    }
}
