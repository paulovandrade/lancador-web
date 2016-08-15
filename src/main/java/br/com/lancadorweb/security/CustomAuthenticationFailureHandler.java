package br.com.lancadorweb.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by paulo on 06/08/16.
 */
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler{

    private final String USERNOTFOUND_MSG			                    = "Usuário não encontrado";
    private final String BADCREDENTIALSEXCEPTION_MSG                    = "Usuário e(ou) senha incorretos. Tente novamente";
    private final String SESSIONAUTHENTICATIONEXCEPTION_MSG             = "Sessao expirada. Por favor, faça login novamente";
    private final String DISABLEDEXCEPTION_MSG                          = "Conta desativada";
    private final String AUTHENTICATIONCREDENTIALSNOTFOUNDEXCEPTION_MSG = "Usuário não encontrado. Tente novamente";
    private final String CREDENTIALSEXPIREDEXCEPTION_MSG                = "Suas credenciais de acesso estão expiradas. Por favor, entre em contato com o administrador do sistema";
    private final String LOCKEDEXCEPTION_MSG		                    = "Sua conta está bloqueada. Por favor, entre em contato com o administrador do sistema";
    private final String GENERICEXCEPTION_MSG		                    = "Não foi possível realizar o login. Contate o administrador do sistema";

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

    }
}
