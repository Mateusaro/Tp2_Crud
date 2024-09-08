package com.example.Crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/filmes")
public class FilmeController {
    @Autowired
    private RabbitMQService rabbitMQService;  // Usando RabbitMQService em vez de RabbitMQProducer

    @Autowired FilmeHistoricoRepository filmeHistoricoRepository;

    @Autowired
    private FilmesRepository filmeRepository;

    @Autowired
    private FilmeService filmeService;

    @GetMapping
    public List<Filmes> getAllFilmes() {
        return filmeService.getAllFilmes();
    }

    @GetMapping("/{id}")
    public Filmes getFilmeById(@PathVariable Long id) {
        return filmeService.getFilmeById(id);
    }

    @PostMapping
    public Filmes saveFilme(
            @RequestBody Filmes filme,
            @RequestParam Long cinemaId,
            @RequestParam String cep
    ) {
        // Salva o filme e envia uma mensagem para a fila
        Filmes savedFilme = filmeService.saveFilme(filme, cinemaId, cep);
        String message = String.format("Novo filme criado: %s no cinema com ID %d e CEP %s",
                savedFilme.getTitulo(), cinemaId, cep);
        rabbitMQService.sendMessage(message);
        return savedFilme;
    }

    @PutMapping("/{id}")
    public Filmes updateFilme(@PathVariable Long id, @RequestBody Filmes filme) {
        return filmeService.updateFilme(id, filme);
    }

    @DeleteMapping("/{id}")
    public void deleteFilme(@PathVariable Long id) {
        filmeService.deleteFilme(id);
    }
    /*
    @GetMapping("/{id}/historico")
    public List<FilmeHistorico> getHistoricoByFilmeId(@PathVariable Long id) {
        return filmeService.getHistoricoByFilmeId(id);
    }
     */
    @GetMapping("/cinema/{cinemaId}/historico")
    public List<FilmeHistorico> getHistoricoByCinemaId(@PathVariable Long cinemaId) {
        return filmeService.getHistoricoByCinemaId(cinemaId);
    }
}
