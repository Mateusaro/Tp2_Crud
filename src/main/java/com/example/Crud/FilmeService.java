package com.example.Crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmeService {

    @Autowired
    private FilmesRepository filmeRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private FilmeHistoricoRepository filmeHistoricoRepository;

    @Autowired
    private EnderecoService enderecoService; // Serviço que consome a API ViaCEP

    @Autowired
    private CepClient cepClient;

    public List<Filmes> getAllFilmes() {
        return filmeRepository.findAll();
    }

    public Filmes getFilmeById(Long id) {
        Optional<Filmes> optionalFilme = filmeRepository.findById(id);
        return optionalFilme.orElse(null); // Se não encontrar o filme, retorna null
    }

    public Filmes saveFilme(Filmes filme, Long cinemaId, String cep) {
        // Buscar cinema pelo ID
        Optional<Cinema> optionalCinema = cinemaRepository.findById(cinemaId);
        if (optionalCinema.isEmpty()) {
            throw new RuntimeException("Cinema não encontrado");
        }

        Cinema cinema = optionalCinema.get();

        // Puxar informações de endereço pelo CEP
        CepResponse cepResponse = cepClient.getEnderecoPorCep(cep);

        // Atualizar dados do cinema com as informações do CEP
        cinema.setLogradouro(cepResponse.getLogradouro());
        cinema.setBairro(cepResponse.getBairro());
        cinema.setLocalidade(cepResponse.getLocalidade());
        cinema.setUf(cepResponse.getUf());
        cinemaRepository.save(cinema);

        // Associar o cinema ao filme
        filme.setCinema(cinema);

        // Salvar o filme
        Filmes savedFilme = filmeRepository.save(filme);

        // Registrar histórico de criação
        registrarHistorico(savedFilme, "CREATE", cinema);

        return savedFilme;
    }

    public void deleteFilme(Long id) {
        Optional<Filmes> optionalFilme = filmeRepository.findById(id);
        if (optionalFilme.isPresent()) {
            Filmes filme = optionalFilme.get();
            registrarHistorico(filme, "DELETE", filme.getCinema());
            filmeRepository.deleteById(id);
        }
    }

    public Filmes updateFilme(Long id, Filmes filme) {
        Optional<Filmes> optionalExistingFilme = filmeRepository.findById(id);
        if (optionalExistingFilme.isEmpty()) {
            return null;
        }

        Filmes existingFilme = optionalExistingFilme.get();
        existingFilme.setTitulo(filme.getTitulo());
        existingFilme.setDiretor(filme.getDiretor());
        existingFilme.setAnoLancamento(filme.getAnoLancamento());

        Filmes updatedFilme = filmeRepository.save(existingFilme);
        registrarHistorico(updatedFilme, "UPDATE", updatedFilme.getCinema());
        return updatedFilme;
    }

    private void registrarHistorico(Filmes filme, String acao, Cinema cinema) {
        FilmeHistorico historico = new FilmeHistorico();
        historico.setFilmeId(filme.getId());
        historico.setTitulo(filme.getTitulo());
        historico.setDiretor(filme.getDiretor());
        historico.setAnoLancamento(filme.getAnoLancamento());
        historico.setAcao(acao);
        historico.setDataAlteracao(LocalDateTime.now());
        historico.setCinema(cinema);
        filmeHistoricoRepository.save(historico);
    }

    public List<FilmeHistorico> getHistoricoByCinemaId(Long cinemaId) {
        //List<Filmes> filmes = filmeRepository.findByCinemaId(cinemaId);
        return filmeHistoricoRepository.findByCinemaId(cinemaId);
    }
}