package br.com.lancadorweb.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by paulo on 26/07/16.
 */
public class CustomSecurityUserDatails extends UsuarioSecurity implements UserDetails {

    public CustomSecurityUserDatails(UsuarioSecurity user) {
        if (user != null) {
            this.setId(user.getId());
            this.setNome(user.getNome());
            this.setLogin(user.getLogin());
            this.setEmail(user.getEmail());
            this.setSenha(user.getSenha());
            this.setAtivo(user.getAtivo());
            this.setAdmin(user.getAdmin());
            this.setUltimoAcesso(user.getUltimoAcesso());
            this.setPermissoes(user.getPermissoes());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.getAdmin().equals(new Character('S'))?"ROLE_ADMINISTRADOR": "ROLE_USUARIO");
        authorities.add(authority);
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getSenha();
    }

    @Override
    public String getUsername() {
        return super.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
