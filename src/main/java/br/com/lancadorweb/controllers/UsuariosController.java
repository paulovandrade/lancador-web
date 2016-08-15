package br.com.lancadorweb.controllers;

import br.com.lancadorweb.DAO.EventoDAO;
import br.com.lancadorweb.DAO.PermissaoDAO;
import br.com.lancadorweb.DAO.UsuarioDAO;
import br.com.lancadorweb.entity.Evento;
import br.com.lancadorweb.entity.Permissao;
import br.com.lancadorweb.entity.Usuario;
import br.com.lancadorweb.entity.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulo on 23/07/16.
 */

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired UsuarioDAO usuarioDAO;
    @Autowired EventoDAO eventoDAO;
    @Autowired PermissaoDAO permissaoDAO;

    @RequestMapping(method = RequestMethod.GET)
    private ModelAndView usuarios(){
        ModelAndView model = new ModelAndView();
        List<Usuario> usuarios = new ArrayList<>();
        try {
            usuarios = usuarioDAO.buscaTodos();
            if (usuarios != null && usuarios.size() > 0) {
                model.addObject("listUsuarios", usuarios);
            } else {
                model.addObject("warning", "Nenhum Usuário encontrado!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addObject("error", "Erro ao carregar a lista de Usuários!");
        }
        model.setViewName("usuarios/gerenciadorusuarios");
        return model;
    }

    @RequestMapping(value = "/cadastro/novo", method = RequestMethod.GET)
    private ModelAndView cadastroUsuario(){
        ModelAndView model = new ModelAndView();
        try {
            model.addObject("usuario", (new UsuarioDTO()));
            model.addObject("titulo", "Cadastro de Novo Usuário");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        model.setViewName("usuarios/cadastrousuario");
        return model;
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    private ModelAndView buscaUsuario(@RequestParam(value = "id", required = true) Integer id) {
        ModelAndView model = new ModelAndView();
        try {
            Usuario user = new Usuario();
            user = usuarioDAO.buscaUsuario(id);
            model.addObject("usuario", (new UsuarioDTO(user.getId(), user.getNome(), user.getLogin(), user.getEmail(), user.getSenha(), user.getAdmin().equals('S')?true:false, user.getAtivo().equals('S') ? true : false)));
            model.addObject("titulo", "Edição de Usuário");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        model.setViewName("usuarios/cadastrousuario");
        return model;
    }

    @RequestMapping(value = "/cadastro/save", method = RequestMethod.POST)
    private ModelAndView usuarioSave(@ModelAttribute(value="usuario") UsuarioDTO userDTO){
        ModelAndView model = new ModelAndView();
        boolean novo = false;
        Usuario usuario = null;
        try {
            if (userDTO.getId() != null && userDTO.getId() > 0) {
                novo = false;
                usuario = usuarioDAO.buscaUsuario(userDTO.getId().intValue());
                usuario.setNome(userDTO.getNome());
                usuario.setLogin(userDTO.getLogin());
                usuario.setEmail(userDTO.getEmail());
                usuario.setSenha(userDTO.getSenha());
                usuario.setAdmin(userDTO.getAdmin() ? 'S' : 'N');
                usuario.setAtivo(userDTO.getAtivo() ? 'S' : 'N');
            } else {
                novo = true;
                usuario = new Usuario(userDTO.getNome(), userDTO.getLogin(), userDTO.getEmail(), userDTO.getSenha(), userDTO.getAdmin()?'S':'N', userDTO.getAtivo()?'S':'N');
            }
            Usuario usuariosalvo = usuarioDAO.salvarUsuario(usuario);
            if (novo) {
                List<Evento> eventoList = new ArrayList<>();
                eventoList = eventoDAO.buscaTodos();
                eventoList.forEach(evento -> {
                    Permissao p = new Permissao(usuariosalvo, evento, 'N');
                    permissaoDAO.salvarPermissao(p);
                });
            }
            if (usuariosalvo != null) {
                model.addObject("success", "Usuário salvo com sucesso!");
                model.addObject("titulo", "Cadastro de Novo Usuário");
                model.addObject("usuario", new UsuarioDTO());
            } else {
                model.addObject("error", "Erro ao salvar o Usuário!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addObject("error", "Erro ao salvar o Usuário!");
        }
        model.setViewName("usuarios/cadastrousuario");
        return model;
    }
}
