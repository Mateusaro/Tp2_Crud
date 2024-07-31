package com.example.Crud;

import java.util.List;

public interface FilmeHistoricoRepository {
    List<FilmeHistorico> findAll();
    List<FilmeHistorico> findByFilmeId(Long filmeId);
    FilmeHistorico save(FilmeHistorico filmeHistorico);
}
