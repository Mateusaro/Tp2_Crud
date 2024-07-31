package com.example.Crud;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FilmeServiceTest {

    @Mock
    private FilmesRepository filmesRepository;

    @Mock
    private FilmeHistoricoRepository filmeHistoricoRepository;

    @InjectMocks
    private FilmeService filmeService;

    public FilmeServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveFilme() {
        // Arrange
        Filmes filme = new Filmes();
        filme.setTitulo("Inception");
        filme.setDiretor("Christopher Nolan");
        filme.setAnoLancamento(2010);

        when(filmesRepository.save(any(Filmes.class))).thenReturn(filme);

        FilmeHistorico historico = new FilmeHistorico();
        historico.setFilmeId(filme.getId());
        historico.setTitulo(filme.getTitulo());
        historico.setDiretor(filme.getDiretor());
        historico.setAnoLancamento(filme.getAnoLancamento());
        historico.setAcao("CREATE");
        historico.setDataAlteracao(LocalDateTime.now());

        when(filmeHistoricoRepository.save(any(FilmeHistorico.class))).thenReturn(historico);

        // Act
        Filmes savedFilme = filmeService.saveFilme(filme);

        // Assert
        assertEquals(filme.getTitulo(), savedFilme.getTitulo());
        assertEquals(filme.getDiretor(), savedFilme.getDiretor());
        assertEquals(filme.getAnoLancamento(), savedFilme.getAnoLancamento());

        verify(filmesRepository, times(1)).save(filme);
        verify(filmeHistoricoRepository, times(1)).save(any(FilmeHistorico.class));
    }
}
