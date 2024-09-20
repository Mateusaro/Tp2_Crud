package com.example.Crud;

import java.io.Serializable;
import java.time.LocalDateTime;

public class FilmeHistorico implements Serializable {

    private Long id;
    private String titulo;
    private String acao;  // Criação, Atualização, Exclusão
    private LocalDateTime data;  // Data da ação

    public FilmeHistorico() {}

    public FilmeHistorico(Long id, String titulo, String acao, LocalDateTime data) {
        this.id = id;
        this.titulo = titulo;
        this.acao = acao;
        this.data = data;
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

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
