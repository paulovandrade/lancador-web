package br.com.lancadorweb.entity;

import java.io.Serializable;

/**
 * Created by paulo on 20/07/16.
 */

public class PermissaoDTO implements Serializable {

    private Long id;
    private Long idusuario;
    private String nomeUsuario;
    private Long idevento;
    private String nomeEvento;
    private String urlEvento;
    private Boolean permite;

    public PermissaoDTO() {
    }

    public PermissaoDTO(Long id, String nomeUsuario, String nomeEvento, String urlEvento, Boolean permite) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.nomeEvento = nomeEvento;
        this.urlEvento = urlEvento;
        this.permite = permite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Long getIdevento() {
        return idevento;
    }

    public void setIdevento(Long idevento) {
        this.idevento = idevento;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getUrlEvento() {
        return urlEvento;
    }

    public void setUrlEvento(String urlEvento) {
        this.urlEvento = urlEvento;
    }

    public Boolean getPermite() {
        return permite;
    }

    public void setPermite(Boolean permite) {
        this.permite = permite;
    }
}
