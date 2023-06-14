package br.com.senac.webpage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.webpage.util.IdSession;

@Controller
@RequestMapping("/paginaEscolherLista")
public class EscolherListaController {

    @GetMapping
    public ModelAndView init(final Model model) {  
    	if(IdSession.idMain ==null || !IdSession.idType.equals("Administrador")) {
    	ModelAndView modelAndView = new ModelAndView("redirect:login");
    	System.out.println("login");
    	return modelAndView;
    	}else{
    		ModelAndView modelAndView = new ModelAndView("paginaEscolherLista");
        	System.out.println("pagina");
        	return modelAndView;
        	}
    }
    
}