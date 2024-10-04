package com.example.Crud.controller;

import com.example.Crud.model.Filme;
import com.example.Crud.service.FilmeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class FilmeCreateTest {

    @InjectMocks
    private FilmeController filmeController;

    @Mock
    private FilmeService filmeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateFilme() {
        Filme filme = new Filme();
        filme.setId(1L);
        filme.setTitulo("Filme de Teste");
        filme.setDiretor("Diretor Teste");
        filme.setAnoLancamento(2023);
        filme.setCinemaId(1L);
        filme.setUltimaAcao("Criado");
        filme.setDataUltimaAlteracao(LocalDateTime.now());

        when(filmeService.createFilme(any(Filme.class))).thenReturn(filme);

        Filme createdFilme = filmeController.createFilme(filme);

        assertEquals(filme.getId(), createdFilme.getId());
        assertEquals(filme.getTitulo(), createdFilme.getTitulo());
        assertEquals(filme.getDiretor(), createdFilme.getDiretor());
        assertEquals(filme.getAnoLancamento(), createdFilme.getAnoLancamento());
        assertEquals(filme.getCinemaId(), createdFilme.getCinemaId());
    }
}
