package br.com.lancadorweb.controllers;

import br.com.lancadorweb.security.UsuarioSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping(value = {"/","/login"})
    private ModelAndView login(){
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "/home")
    private ModelAndView home(){
        ModelAndView model = new ModelAndView();
        UsuarioSecurity userSec = new UsuarioSecurity();
        try {

        } catch (Exception e) {
            userSec = (UsuarioSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addObject("usuario", userSec);
        }
        model.setViewName("home");
        return model;
    }
}
