package com.example.Crud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CinemaServiceTest {

    @Mock
    private CinemaRepository cinemaRepository;

    @Mock
    private CepClient cepClient;

    @InjectMocks
    private CinemaService cinemaService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCinema() {
        // Arrange
        Cinema cinema = new Cinema();
        cinema.setId(1L);
        cinema.setNome("Cineplex");

        CepResponse cepResponse = new CepResponse();
        cepResponse.setLogradouro("Rua dos Exemplo");
        cepResponse.setBairro("Bairro Exemplo");
        cepResponse.setLocalidade("Cidade Exemplo");
        cepResponse.setUf("UF");

        when(cepClient.getEnderecoPorCep("22775046")).thenReturn(cepResponse);
        when(cinemaRepository.save(any(Cinema.class))).thenReturn(cinema);

        // Act
        Cinema createdCinema = cinemaService.createCinema(cinema, "22775046");

        // Assert
        assertEquals("Cineplex", createdCinema.getNome());
        assertEquals("Rua dos Exemplo", createdCinema.getLogradouro());
        assertEquals("Bairro Exemplo", createdCinema.getBairro());
        assertEquals("Cidade Exemplo", createdCinema.getLocalidade());
        assertEquals("UF", createdCinema.getUf());
    }
}
