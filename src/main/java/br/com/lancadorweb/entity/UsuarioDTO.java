package br.com.lancadorweb.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by paulo on 20/07/16.
 */

public class UsuarioDTO implements Serializable{

    private Long id;
    private String nome;
    private String login;
    private String email;
    private String senha;
    private Boolean admin = false;
    private Boolean ativo = true;
    private Date ultimoAcesso;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String nome, String login, String email, String senha, Boolean admin, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.senha = senha;
        this.admin = admin;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public Boolean getAdmin() { return admin; }

    public void setAdmin(Boolean admin) { this.admin = admin; }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtivo() { return ativo; }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Date getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(Date ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }
}
