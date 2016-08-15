package br.com.lancadorweb.security;

import br.com.lancadorweb.entity.Permissao;

import java.util.Date;
import java.util.List;

/**
 * Created by paulo on 26/07/16.
 */
public class UsuarioSecurity {

    private Long id;
    private String nome;
    private String login;
    private String email;
    private String senha;
    private Character admin;
    private Character ativo;
    private Date ultimoAcesso;
    private List<Permissao> permissoes;

    public Long getId() { return id; }

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

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Character getAdmin() { return admin; }

    public void setAdmin(Character admin) { this.admin = admin; }

    public Character getAtivo() {
        return ativo;
    }

    public void setAtivo(Character ativo) {
        this.ativo = ativo;
    }

    public Date getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(Date ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }
}
