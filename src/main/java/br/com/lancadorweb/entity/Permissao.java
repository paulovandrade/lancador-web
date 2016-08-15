package br.com.lancadorweb.entity;

import java.io.Serializable;

/**
 * Created by paulo on 20/07/16.
 */

public class Permissao implements Serializable {

    private Long id;
    private Usuario usuario;
    private Evento evento;
    private Character permite;

    public Permissao() {
    }

    public Permissao(Usuario usuario, Evento evento, Character permite) {
        this.usuario = usuario;
        this.evento = evento;
        this.permite = permite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Character getPermite() {
        return permite;
    }

    public void setPermite(Character permite) {
        this.permite = permite;
    }
}
