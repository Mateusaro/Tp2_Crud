package com.example.Crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FilmeService {
    @Autowired
    private FilmesRepository filmeRepository;

    @Autowired
    private FilmeHistoricoRepository filmeHistoricoRepository;

    public List<Filmes> getAllFilmes() {
        return filmeRepository.findAll();
    }

    public Filmes getFilmeById(Long id) {
        return filmeRepository.findById(id);
    }

    public Filmes saveFilme(Filmes filme) {
        Filmes savedFilme = filmeRepository.save(filme);
        registrarHistorico(savedFilme, "CREATE");
        return savedFilme;
    }

    public void deleteFilme(Long id) {
        Filmes filme = filmeRepository.findById(id);
        if (filme != null) {
            registrarHistorico(filme, "DELETE");
            filmeRepository.deleteById(id);
        }
    }

    public Filmes updateFilme(Long id, Filmes filme) {
        Filmes existingFilme = filmeRepository.findById(id);
        if (existingFilme == null) {
            return null;
        }
        existingFilme.setTitulo(filme.getTitulo());
        existingFilme.setDiretor(filme.getDiretor());
        existingFilme.setAnoLancamento(filme.getAnoLancamento());
        Filmes updatedFilme = filmeRepository.save(existingFilme);
        registrarHistorico(updatedFilme, "UPDATE");
        return updatedFilme;
    }

    private void registrarHistorico(Filmes filme, String acao) {
        FilmeHistorico historico = new FilmeHistorico();
        historico.setFilmeId(filme.getId());
        historico.setTitulo(filme.getTitulo());
        historico.setDiretor(filme.getDiretor());
        historico.setAnoLancamento(filme.getAnoLancamento());
        historico.setAcao(acao);
        historico.setDataAlteracao(LocalDateTime.now());
        filmeHistoricoRepository.save(historico);
    }

    public List<FilmeHistorico> getHistoricoByFilmeId(Long id) {
        return filmeHistoricoRepository.findByFilmeId(id);
    }
}
