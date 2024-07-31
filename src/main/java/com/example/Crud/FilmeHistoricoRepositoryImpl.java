package com.example.Crud;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FilmeHistoricoRepositoryImpl implements FilmeHistoricoRepository {
    private List<FilmeHistorico> historicos = new ArrayList<>();
    private Long idCounter = 1L;

    @Override
    public List<FilmeHistorico> findAll() {
        return historicos;
    }

    @Override
    public List<FilmeHistorico> findByFilmeId(Long filmeId) {
        List<FilmeHistorico> result = new ArrayList<>();
        for (FilmeHistorico historico : historicos) {
            if (historico.getFilmeId().equals(filmeId)) {
                result.add(historico);
            }
        }
        return result;
    }

    @Override
    public FilmeHistorico save(FilmeHistorico filmeHistorico) {
        filmeHistorico.setId(idCounter++);
        historicos.add(filmeHistorico);
        return filmeHistorico;
    }
}
