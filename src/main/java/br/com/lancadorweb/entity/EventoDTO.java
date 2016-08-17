package br.com.lancadorweb.entity;

import java.io.Serializable;

/**
 * Created by paulo on 20/07/16.
 */

public class EventoDTO implements Serializable{

    private Long id;
    private String descricao;
    private String url;
    private String icone;
    private Boolean ativa;

    public EventoDTO() {
    }

    public EventoDTO(Long id, String descricao, String url, String icone, Boolean ativa) {
        this.id = id;
        this.descricao = descricao;
        this.url = url;
        this.icone = icone;
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

    public Boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }
}
