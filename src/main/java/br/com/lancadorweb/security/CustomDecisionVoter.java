package br.com.lancadorweb.security;

import br.com.lancadorweb.entity.Permissao;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by paulo on 06/08/16.
 */

public class CustomDecisionVoter implements AccessDecisionVoter<Object> {
    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }

    public int vote(Authentication authentication, Object o, Collection<ConfigAttribute> collection) {
        FilterInvocation f = (FilterInvocation) o;
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        String urlSemParametros = f.getRequestUrl();
        int pos = urlSemParametros.indexOf("?");

        if (pos >= 0) {
            urlSemParametros = f.getRequestUrl().substring(0, pos);
        }

        if (f.getRequestUrl().startsWith("/login") || f.getRequestUrl().startsWith("/logout")|| f.getRequestUrl().startsWith("/resources")){
            return ACCESS_GRANTED;
        }

        if (roles.contains("ROLE_ADMINISTRADOR")) {
            return ACCESS_GRANTED;
        }

        UsuarioSecurity usuarioAtual = null;

        if (authentication.getPrincipal() instanceof UsuarioSecurity){
            usuarioAtual = (UsuarioSecurity)authentication.getPrincipal();

            List<Permissao> p = usuarioAtual.getPermissoes();

            if (p != null) {
                for (Permissao permissao : p) {
                    if (permissao.getEvento().getUrl().equals(urlSemParametros)){
                        return ACCESS_GRANTED;
                    }
                }
            }
            return ACCESS_DENIED;
        }
        return ACCESS_ABSTAIN;
    }


}
