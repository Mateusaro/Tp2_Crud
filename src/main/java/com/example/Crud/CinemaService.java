package com.example.Crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private CepClient cepClient;

    public Cinema createCinema(Cinema cinema, String cep) {
        // Puxar informações de endereço pelo CEP
        CepResponse cepResponse = cepClient.getEnderecoPorCep(cep);

        // Atualizar dados do cinema com as informações do CEP
        cinema.setCep(cep); // Aqui, atribua o CEP recebido
        cinema.setLogradouro(cepResponse.getLogradouro());
        cinema.setBairro(cepResponse.getBairro());
        cinema.setLocalidade(cepResponse.getLocalidade());
        cinema.setUf(cepResponse.getUf());

        return cinemaRepository.save(cinema);
    }

    public List<Cinema> getAllCinemas() {
        return cinemaRepository.findAll();
    }

    public Cinema getCinemaById(Long id) {
        return cinemaRepository.findById(id).orElseThrow(() -> new RuntimeException("Cinema not found"));
    }
}
