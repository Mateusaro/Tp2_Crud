package com.example.Crud.service;

import com.example.Crud.CepResponse;
import com.example.Crud.rabbit.RabbitService;
import com.example.Crud.feign.CinemaClient;
import com.example.Crud.model.Cinema;
import com.example.Crud.model.CinemaHistorico;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CinemaService {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private CinemaClient cinemaClient;

    @Autowired
    private RabbitService rabbitService;

    public Cinema createCinema(String nome, String cep) {
        CepResponse cepResponse = enderecoService.getEnderecoPorCep(cep);

        Cinema cinema = new Cinema();
        cinema.setNome(nome);
        cinema.setCep(cepResponse.getCep());
        cinema.setLogradouro(cepResponse.getLogradouro());
        cinema.setBairro(cepResponse.getBairro());
        cinema.setLocalidade(cepResponse.getLocalidade());
        cinema.setUf(cepResponse.getUf());
        rabbitService.sendCinemaMessage(cinema);

        log.info("Cinema Criado: " + cinema);


        return cinema;
    }

    public List<Cinema> getCinemas() {
        return cinemaClient.getCinemas();
    }


    public Cinema updateCinema(Long id, Cinema cinema) {
        Cinema updatedCinema = cinemaClient.updateCinema(id, cinema);

        rabbitService.sendCinemaMessage(updatedCinema);
        log.info("Cinema Atualizado: " + updatedCinema);


        return updatedCinema;
    }

    public void deleteCinema(Long id) {
        cinemaClient.deleteCinema(id);

        log.info("Cinema Deletado: " + id);

        rabbitService.sendCinemaMessage(new Cinema(id));
    }

}
