package br.com.senac.webpage.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.senac.webpage.dao.UsuarioDAO;
import br.com.senac.webpage.model.UsuarioDto;
import br.com.senac.webpage.util.Cripto;

@Controller
@RequestMapping("/paginaEscolherLista")
public class EscolherListaController {

    @GetMapping
    public String init(final Model model) {  	
    	model.addAttribute("usuarioDto", new UsuarioDto());
    	System.out.println("init");
    	return "paginaEscolherLista";
    }
    
}