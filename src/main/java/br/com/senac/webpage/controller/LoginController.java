package br.com.senac.webpage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.senac.webpage.model.Usuario;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String init(final Usuario usuario) {
    	System.out.println("bati");
        return "paginaUsuarioBackoffice";
        
    }
    @PostMapping
    public void requestLogin() {
    	System.out.println("bati");
        
    }
   
}