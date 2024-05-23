package com.example.Crud;

import com.example.Crud.Filmes;
import com.example.Crud.FilmesRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FilmeRepositoryImpl implements FilmesRepository{
    private List<Filmes> filmes = new ArrayList<>();
    private Long idCounter = 1L;

    @Override
    public List<Filmes> findAll() {
        return filmes;
    }

    @Override
    public Filmes findById(Long id) {
        for (Filmes filme : filmes) {
            if (filme.getId().equals(id)) {
                return filme;
            }
        }
        return null;
    }

    @Override
    public Filmes save(Filmes filme) {
        if (filme.getId() == null) {
            filme.setId(idCounter++);
        } else {
            deleteById(filme.getId());
        }
        filmes.add(filme);
        return filme;
    }

    @Override
    public void deleteById(Long id) {
        filmes.removeIf(filme -> filme.getId().equals(id));
    }
}
