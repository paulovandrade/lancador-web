package br.com.lancadorweb.security;

import br.com.lancadorweb.DAO.PermissaoDAO;
import br.com.lancadorweb.DAO.UsuarioDAO;
import br.com.lancadorweb.entity.Permissao;
import br.com.lancadorweb.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by paulo on 26/07/16.
 */
@Component
public class CustomSecurityUserDetailsService implements UserDetailsService {

    @Autowired private UsuarioDAO usuarioDAO;
    @Autowired private PermissaoDAO permissaoDAO;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Usuario usuario = new Usuario();
        UsuarioSecurity usuarioSecurity = new UsuarioSecurity();
        List<Permissao> permissoes = new ArrayList<>();
        usuario = usuarioDAO.buscaUsuarioPorLogin(s);
        if (usuario != null) {
            usuarioSecurity.setId(usuario.getId());
            usuarioSecurity.setNome(usuario.getNome());
            usuarioSecurity.setLogin(usuario.getLogin());
            usuarioSecurity.setEmail(usuario.getEmail());
            usuarioSecurity.setSenha(usuario.getSenha());
            usuarioSecurity.setAtivo(usuario.getAtivo());
            usuarioSecurity.setAdmin(usuario.getAdmin());
            usuarioSecurity.setUltimoAcesso(usuario.getUltimoAcesso());
            permissoes = permissaoDAO.buscaPermissoesUsuarioPermiteSim(usuarioSecurity.getId().intValue());
            usuarioSecurity.setPermissoes(permissoes);
            atualizaUltimoAcesso(usuario);
            if (usuarioSecurity.getAtivo().equals("N")) {
                throw new UsernameNotFoundException("Usuário Desativado.");
            }
        } else {
            throw new UsernameNotFoundException("Usuário de login : "+s+" não encontrado.");
        }
        return new CustomSecurityUserDatails(usuarioSecurity);
    }

    private void atualizaUltimoAcesso(Usuario usuario){
        usuario.setUltimoAcesso(new Date());
        usuarioDAO.salvarUsuario(usuario);
    }
}
