package br.com.lancadorweb.entity;

import java.io.Serializable;

/**
 * Created by paulo on 20/07/16.
 */

public class Evento implements Serializable{

    private Long id;
    private String descricao;
    private String url;
    private String icone;
    private Character ativa;

    public Evento() {
    }

    public Evento(String descricao, String url, Character ativa) {
        this.descricao = descricao;
        this.url = url;
        this.ativa = ativa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcone() { return icone; }

    public void setIcone(String icone) { this.icone = icone; }

    public Character getAtiva() {
        return ativa;
    }

    public void setAtiva(Character ativa) {
        this.ativa = ativa;
    }
}
