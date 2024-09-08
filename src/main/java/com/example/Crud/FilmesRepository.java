package com.example.Crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmesRepository extends JpaRepository<Filmes, Long> {
    List<Filmes> findByCinemaId(Long cinemaId);
}