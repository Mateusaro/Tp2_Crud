package com.example.Crud;

import java.util.List;

public interface FilmesRepository {
    List<Filmes> findAll();
    Filmes findById(Long id);
    Filmes save(Filmes filme);
    void deleteById(Long id);
}
