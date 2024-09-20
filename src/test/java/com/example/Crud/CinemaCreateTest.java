package com.example.Crud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.*;

@WebMvcTest(CinemaController.class)
public class CinemaCreateTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CinemaService cinemaService;

    @MockBean
    private FilmeService filmeService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateCinema() throws Exception {
        // Dados do Cinema
        CinemaDTO cinemaDTO = new CinemaDTO();
        cinemaDTO.setNome("Cineplex Rio");
        cinemaDTO.setCep("22775046");  // CEP específico

        Cinema createdCinema = new Cinema();
        createdCinema.setId(1L);
        createdCinema.setNome("Cineplex Rio");
        createdCinema.setCep("22775046");
        createdCinema.setLogradouro("Avenida das Américas");
        createdCinema.setBairro("Barra da Tijuca");
        createdCinema.setLocalidade("Rio de Janeiro");
        createdCinema.setUf("RJ");

        when(cinemaService.createCinema("Cineplex Rio", "22775046")).thenReturn(createdCinema);

        mockMvc.perform(MockMvcRequestBuilders.post("/cinemas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cinemaDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Cineplex Rio"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cep").value("22775046"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.logradouro").value("Avenida das Américas"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bairro").value("Barra da Tijuca"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.localidade").value("Rio de Janeiro"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.uf").value("RJ"));

        verify(cinemaService, times(1)).createCinema("Cineplex Rio", "22775046");
    }
}
