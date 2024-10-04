package com.example.Crud.model;

import java.time.LocalDateTime;

public class CinemaHistorico {
    private Long id;
    private Long cinemaId;
    private String nome;
    private String acao;
    private LocalDateTime dataAlteracao;

    public CinemaHistorico(Long id, Long cinemaId, String nome, String acao, LocalDateTime dataAlteracao) {
        this.id = id;
        this.cinemaId = cinemaId;
        this.nome = nome;
        this.acao = acao;
        this.dataAlteracao = dataAlteracao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Long cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}
