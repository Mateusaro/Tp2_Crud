package com.example.Crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {
    @Autowired
    private FilmesRepository filmeRepository;

    public List<Filmes> getAllFilmes() {
        return filmeRepository.findAll();
    }

    public Filmes getFilmeById(Long id) {
        return filmeRepository.findById(id);
    }

    public Filmes saveFilme(Filmes filme) {
        return filmeRepository.save(filme);
    }

    public void deleteFilme(Long id) {
        filmeRepository.deleteById(id);
    }
}
