package br.com.lancadorweb.controllers;

import br.com.lancadorweb.DAO.EventoDAO;
import br.com.lancadorweb.DAO.PermissaoDAO;
import br.com.lancadorweb.DAO.UsuarioDAO;
import br.com.lancadorweb.entity.Evento;
import br.com.lancadorweb.entity.EventoDTO;
import br.com.lancadorweb.entity.Permissao;
import br.com.lancadorweb.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulo on 26/07/16.
 */

@Controller
@RequestMapping("/eventos")
public class EventosController {

    @Autowired EventoDAO eventoDAO;
    @Autowired UsuarioDAO usuarioDAO;
    @Autowired PermissaoDAO permissaoDAO;

    @RequestMapping(method = RequestMethod.GET)
    private ModelAndView eventos(){
        ModelAndView model = new ModelAndView();
        List<Evento> eventos = new ArrayList<>();
        try {
            eventos = eventoDAO.buscaTodos();
            if (eventos != null && eventos.size() > 0) {
                model.addObject("listEventos", eventos);
            } else {
                model.addObject("warning", "Nenhum Evento encontrado!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addObject("error", "Erro ao carregar a lista de Eventos!");
        }
        model.setViewName("eventos/gerenciadoreventos");
        return model;
    }

    @RequestMapping(value = "/cadastro/novo", method = RequestMethod.GET)
    private ModelAndView cadastroEvento(){
        ModelAndView model = new ModelAndView();
        try {
            model.addObject("evento", (new EventoDTO()));
            model.addObject("titulo", "Cadastro de Novo Evento");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        model.setViewName("eventos/cadastroevento");
        return model;
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    private ModelAndView buscaEvento(@RequestParam("id") Integer id){
        ModelAndView model = new ModelAndView();
        try {
            Evento evento = new Evento();
            evento = eventoDAO.buscaEvento(id);
            model.addObject("evento", (new EventoDTO(evento.getId(), evento.getDescricao(), evento.getUrl(), evento.getAtiva().equals('S')?true:false)));
            model.addObject("titulo", "Edição de Evento");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        model.setViewName("eventos/cadastroevento");
        return model;
    }

    @RequestMapping(value = "/cadastro/save", method = RequestMethod.POST)
    private ModelAndView eventoSave(@ModelAttribute(value="evento") EventoDTO eventoDTO){
        ModelAndView model = new ModelAndView();
        boolean novo = false;
        Evento evento = null;
        try {
            if (eventoDTO.getId() != null && eventoDTO.getId() > 0) {
                novo = false;
                evento = eventoDAO.buscaEvento(eventoDTO.getId().intValue());
                evento.setDescricao(eventoDTO.getDescricao());
                evento.setUrl(eventoDTO.getUrl());
                evento.setAtiva(eventoDTO.getAtiva()?'S':'N');
            } else {
                novo = true;
                evento = new Evento(eventoDTO.getDescricao(), eventoDTO.getUrl(), eventoDTO.getAtiva()?'S':'N');
            }
            Evento eventosalvo = eventoDAO.salvarEvento(evento);
            if (novo) {
                List<Usuario> usuarioList = new ArrayList<>();
                usuarioList = usuarioDAO.buscaTodos();
                for (Usuario usuario : usuarioList) {
                    Permissao p = new Permissao(usuario, eventosalvo, 'N');
                    permissaoDAO.salvarPermissao(p);
                }
            }
            if (eventosalvo != null) {
                model.addObject("success", "Evento salvo com sucesso!");
                model.addObject("titulo", "Cadastro de Novo Evento");
                model.addObject("evento", new EventoDTO());
            } else {
                model.addObject("error", "Erro ao salvar o Evento!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addObject("error", "Erro ao salvar o Evento!");
        }
        model.setViewName("eventos/cadastroevento");
        return model;
    }
}
