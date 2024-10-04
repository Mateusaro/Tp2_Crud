package com.example.Crud.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Filme implements Serializable {

    private Long id;
    private String titulo;
    private String diretor;
    private int anoLancamento;
    private Long cinemaId;

    private String ultimaAcao;
    private LocalDateTime dataUltimaAlteracao;

    public Filme() {}

    public Filme(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public Long getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Long cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getUltimaAcao() {
        return ultimaAcao;
    }

    public void setUltimaAcao(String ultimaAcao) {
        this.ultimaAcao = ultimaAcao;
    }

    public LocalDateTime getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(LocalDateTime dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }
}
