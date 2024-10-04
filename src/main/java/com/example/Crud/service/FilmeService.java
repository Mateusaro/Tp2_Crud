package com.example.Crud.service;

import com.example.Crud.rabbit.RabbitService;
import com.example.Crud.feign.FilmeClient;
import com.example.Crud.model.Filme;
import com.example.Crud.model.FilmeHistorico;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class FilmeService {

    @Autowired
    private FilmeClient filmeClient;

    @Autowired
    private RabbitService rabbitService;

    public Filme createFilme(Filme filme) {
        filme.setUltimaAcao("Criação");
        filme.setDataUltimaAlteracao(LocalDateTime.now());
        rabbitService.sendFilmeMessage(filme);
        log.info("Filme Criado: " + filme);

        return filme;
    }

    public Filme updateFilme(Long id, Filme filme) {
        // Define os valores para auditoria
        filme.setUltimaAcao("Atualização");
        filme.setDataUltimaAlteracao(LocalDateTime.now());

        Filme updatedFilme = filmeClient.updateFilme(id, filme);

        // Envia uma mensagem para a fila de filmes com a atualização
        rabbitService.sendFilmeMessage(updatedFilme);
        log.info("Filme Atualizado: " + updatedFilme);


        return updatedFilme;
    }

    public void deleteFilme(Long id) {
        filmeClient.deleteFilme(id);

        // Cria um filme apenas com o ID e configura os valores para auditoria
        Filme filme = new Filme(id);
        filme.setUltimaAcao("Exclusão");
        filme.setDataUltimaAlteracao(LocalDateTime.now());
        log.info("Filme Deletado: " + id);


        // Envia uma mensagem para a fila de filmes para indicar exclusão
        rabbitService.sendFilmeMessage(filme);
    }

    public List<FilmeHistorico> getHistorico() {
        return filmeClient.getFilmeHistorico();
    }
}
