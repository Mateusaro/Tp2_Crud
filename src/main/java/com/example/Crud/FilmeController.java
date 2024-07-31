package com.example.Crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {
    @Autowired
    private FilmeService filmeService;

    @GetMapping
    public List<Filmes> getAllFilmes() {
        return filmeService.getAllFilmes();
    }

    @GetMapping("/{id}")
    public Filmes getFilmeById(@PathVariable Long id) {
        return filmeService.getFilmeById(id);
    }

    @PostMapping
    public Filmes saveFilme(@RequestBody Filmes filme) {
        return filmeService.saveFilme(filme);
    }

    @DeleteMapping("/{id}")
    public void deleteFilme(@PathVariable Long id) {
        filmeService.deleteFilme(id);
    }

    @PutMapping("/{id}")
    public Filmes updateFilme(@PathVariable Long id, @RequestBody Filmes filme) {
        Filmes existingFilme = filmeService.getFilmeById(id);
        if (existingFilme == null) {
            return null;
        }

        existingFilme.setTitulo(filme.getTitulo());
        existingFilme.setDiretor(filme.getDiretor());
        existingFilme.setAnoLancamento(filme.getAnoLancamento());

        return filmeService.saveFilme(existingFilme);
    }

    @GetMapping("/{id}/historico")
    public List<FilmeHistorico> getHistoricoByFilmeId(@PathVariable Long id) {
        return filmeService.getHistoricoByFilmeId(id);
    }
}
