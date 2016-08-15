package br.com.lancadorweb.controllers;

import br.com.lancadorweb.DAO.PermissaoDAO;
import br.com.lancadorweb.entity.Permissao;
import br.com.lancadorweb.entity.PermissaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulo on 26/07/16.
 */

@Controller
@RequestMapping("/permissoes")
public class PermissoesController {

    @Autowired
    PermissaoDAO permissaoDAO;

    @RequestMapping(method = RequestMethod.GET)
    private ModelAndView permissoes(){
        ModelAndView model = new ModelAndView();
        List<Permissao> permissoes = new ArrayList<>();
        try {
            permissoes = permissaoDAO.buscaTodas();
            if (permissoes != null && permissoes.size() > 0) {
                model.addObject("listPermissoes", permissoes);
            } else {
                model.addObject("warning", "Nenhuma Permissão encontrada!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addObject("error", "Erro ao carregar a lista de Permissões!");
        }
        model.setViewName("permissoes/gerenciadorpermissoes");
        return model;
    }

    @RequestMapping(value = "/cadastro/novo", method = RequestMethod.GET)
    private ModelAndView cadastroPermissao(){
        ModelAndView model = new ModelAndView();
        try {
            model.addObject("permissao", new PermissaoDTO());
            model.addObject("titulo", "Cadastro de Nova Permissão");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        model.setViewName("permissoes/cadastroevento");
        return model;
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    private ModelAndView buscaPermissao(@RequestParam(value = "id", required = true) Integer id){
        ModelAndView model = new ModelAndView();
        try {
            Permissao permissao = new Permissao();
            permissao = permissaoDAO.buscaPermissao(id);
            model.addObject("permissao", (new PermissaoDTO(permissao.getId(), permissao.getUsuario().getNome(), permissao.getEvento().getDescricao(), permissao.getEvento().getUrl(), permissao.getPermite().equals('S')?true:false)));
            model.addObject("titulo", "Edição de Permissão");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        model.setViewName("permissoes/cadastropermissao");
        return model;
    }

    @RequestMapping(value = "/cadastro/save", method = RequestMethod.POST)
    private ModelAndView permissaoSave(@ModelAttribute(value="permissao") PermissaoDTO permissaoDTO){
        ModelAndView model = new ModelAndView();
        Permissao permissao = null;
        try {
            if (permissaoDTO.getId() != null && permissaoDTO.getId() > 0) {
                permissao = permissaoDAO.buscaPermissao(permissaoDTO.getId().intValue());
                permissao.setPermite(permissaoDTO.getPermite()?'S':'N');
            }
            Permissao permissaosalva = permissaoDAO.salvarPermissao(permissao);
            if (permissaosalva != null) {
                model.addObject("success", "Permissão salva com sucesso!");
                model.addObject("titulo", "Edição de Permissão");
                model.addObject("permissao", permissaoDTO);
            } else {
                model.addObject("error", "Erro ao salvar a Permissão!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addObject("error", "Erro ao salvar a Permissão!");
        }
        model.setViewName("permissoes/cadastropermissao");
        return model;
    }
}
