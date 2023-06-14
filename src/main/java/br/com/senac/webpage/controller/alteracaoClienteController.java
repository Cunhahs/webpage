package br.com.senac.webpage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.webpage.model.Usuario;
import br.com.senac.webpage.model.UsuarioDto;

@Controller
@RequestMapping("/alteracaoCliente")
public class alteracaoClienteController {

    @GetMapping
    public String init(final Model model) {
    	return "alteracaoCliente";
        
    }    

//     redirect(final Model model) {  	
//
//    	ModelAndView modelAndView = new ModelAndView("redirect:paginaEscolherLista");
//    	System.out.println("init");
//    	return modelAndView;
//   
}