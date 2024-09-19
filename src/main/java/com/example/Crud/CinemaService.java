package com.example.Crud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        // Obtém o endereço pelo CEP
        CepResponse cepResponse = enderecoService.getEnderecoPorCep(cep);

        // Preenche as informações do cinema com o endereço
        Cinema cinema = new Cinema();
        cinema.setNome(nome);
        cinema.setCep(cepResponse.getCep());
        cinema.setLogradouro(cepResponse.getLogradouro());
        cinema.setBairro(cepResponse.getBairro());
        cinema.setLocalidade(cepResponse.getLocalidade());
        cinema.setUf(cepResponse.getUf());

        // Envia o cinema para o microserviço de cinema
        Cinema createdCinema = cinemaClient.createCinema(cinema);

        // Envia uma mensagem para a fila de cinemas
        rabbitService.sendCinemaMessage(createdCinema);

        log.info("Cinema Criado: " + createdCinema);


        return createdCinema;
    }

    public Cinema getCinemaById(Long id) {
        return cinemaClient.getCinemaById(id);
    }

    public Cinema updateCinema(Long id, Cinema cinema) {
        Cinema updatedCinema = cinemaClient.updateCinema(id, cinema);

        // Envia uma mensagem para a fila de cinemas com a atualização
        rabbitService.sendCinemaMessage(updatedCinema);
        log.info("Cinema Atualizado: " + updatedCinema);


        return updatedCinema;
    }

    public void deleteCinema(Long id) {
        cinemaClient.deleteCinema(id);

        log.info("Cinema Deletado: " + id);

        // Envia uma mensagem para a fila de cinemas para indicar exclusão
        rabbitService.sendCinemaMessage(new Cinema(id)); // Envia o ID apenas
    }
}
