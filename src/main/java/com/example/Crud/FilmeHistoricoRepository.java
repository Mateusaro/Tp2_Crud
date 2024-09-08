package com.example.Crud;

import java.util.List;

// FilmeHistoricoRepository.java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeHistoricoRepository extends JpaRepository<FilmeHistorico, Long> {
    List<FilmeHistorico> findByCinemaId(Long filmeIds);
}
