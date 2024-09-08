package com.example.Crud;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FilmeServiceTest {

    @Mock
    private FilmesRepository filmesRepository;

    @Mock
    private FilmeHistoricoRepository filmeHistoricoRepository;

    @Mock
    private CinemaRepository cinemaRepository;

    @InjectMocks
    private FilmeService filmeService;
    @Autowired
    private CepClient cepClient;

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

        Cinema cinema = new Cinema();
        cinema.setId(1L); // ID do cinema
        cinema.setNome("Cinepolis");

        CepResponse cepResponse = new CepResponse();
        cepResponse.setLogradouro("Rua Exemplo");
        cepResponse.setBairro("Bairro Exemplo");
        cepResponse.setLocalidade("Cidade Exemplo");
        cepResponse.setUf("UF");

        when(cinemaRepository.findById(any(Long.class))).thenReturn(Optional.of(cinema));
        when(cepClient.getEnderecoPorCep(any(String.class))).thenReturn(cepResponse);
        when(filmesRepository.save(any(Filmes.class))).thenReturn(filme);

        FilmeHistorico historico = new FilmeHistorico();
        historico.setFilmeId(filme.getId());
        historico.setTitulo(filme.getTitulo());
        historico.setDiretor(filme.getDiretor());
        historico.setAnoLancamento(filme.getAnoLancamento());
        historico.setAcao("CREATE");
        historico.setDataAlteracao(LocalDateTime.now());
        historico.setCinema(cinema);

        when(filmeHistoricoRepository.save(any(FilmeHistorico.class))).thenReturn(historico);

        // Act
        Filmes savedFilme = filmeService.saveFilme(filme, 1L, "01001000"); // Passando cinemaId e cep

        // Assert
        assertEquals(filme.getTitulo(), savedFilme.getTitulo());
        assertEquals(filme.getDiretor(), savedFilme.getDiretor());
        assertEquals(filme.getAnoLancamento(), savedFilme.getAnoLancamento());

        verify(filmesRepository, times(1)).save(filme);
        verify(filmeHistoricoRepository, times(1)).save(any(FilmeHistorico.class));
        verify(cinemaRepository, times(1)).findById(1L);
        verify(cepClient, times(1)).getEnderecoPorCep("01001000");
    }
}
