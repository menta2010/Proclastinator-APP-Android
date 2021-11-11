package com.example.proclastinator.model;

import java.io.Serializable;

public class Atividade implements Serializable {
    private Long  id ;
    private String termino;
    private String começo;
    private String nome_ativiade;

    public Atividade() {
    }

    public Atividade(String termino, String começo, String nome_ativiade) {
        this.termino = termino;
        this.começo = começo;
        this.nome_ativiade = nome_ativiade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTermino() {
        return termino;
    }

    public void setTermino(String termino) {
        this.termino = termino;
    }

    public String getComeço() {
        return começo;
    }

    public void setComeço(String começo) {
        this.começo = começo;
    }

    public String getNome_ativiade() {
        return nome_ativiade;
    }

    public void setNome_ativiade(String nome_ativiade) {
        this.nome_ativiade = nome_ativiade;
    }

    @Override
    public String toString() {
        return
                        "atividade : " + nome_ativiade + '\n'+
                        "inicio :" + começo + '\n' +
                        "termino='" + termino + '\n'

                ;
    }
}
