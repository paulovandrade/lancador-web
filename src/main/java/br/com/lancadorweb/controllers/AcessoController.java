package br.com.lancadorweb.controllers;

import br.com.lancadorweb.DAO.UsuarioDAO;
import br.com.lancadorweb.entity.Usuario;
import br.com.lancadorweb.entity.UsuarioDTO;
import br.com.lancadorweb.security.UsuarioSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by paulo on 06/08/16.
 */
@Controller
public class AcessoController {

    @Autowired UsuarioDAO usuarioDAO;

    @RequestMapping(value = "/minhaconta", method = RequestMethod.GET)
    private ModelAndView minhaConta(){
        ModelAndView model = new ModelAndView();
        UsuarioSecurity userSec = new UsuarioSecurity();
        try {
            userSec = (UsuarioSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addObject("usuario", userSec);
        } catch (Exception e) {
            e.printStackTrace();
            model.addObject("error", "Erro ao carregar o Usuário Logado!");
        }
        model.setViewName("/minhaconta");
        return model;
    }

    @RequestMapping(value = "/minhaconta/save", method = RequestMethod.POST)
    private ModelAndView minhaContaSave(@ModelAttribute(value="usuario") UsuarioDTO userDTO){
        ModelAndView model = new ModelAndView();
        Usuario usuario = null;
        try {
            usuario = usuarioDAO.buscaUsuario(userDTO.getId().intValue());
            usuario.setNome(userDTO.getNome());
            usuario.setLogin(userDTO.getLogin());
            usuario.setEmail(userDTO.getEmail());
            usuario.setSenha(userDTO.getSenha());
            Usuario usuariosalvo = usuarioDAO.salvarUsuario(usuario);
            if (usuariosalvo != null) {
                model.addObject("success", "Usuário salvo com sucesso!");
                model.addObject("usuario", usuariosalvo);
            } else {
                model.addObject("error", "Erro ao salvar o Usuário!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addObject("error", "Erro ao salvar o Usuário!");
        }
        model.setViewName("minhaconta");
        return model;
    }
}
