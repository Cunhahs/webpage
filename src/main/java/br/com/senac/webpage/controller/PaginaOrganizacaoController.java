package br.com.senac.webpage.controller;

import java.sql.SQLException;
import java.util.List;

import org.apache.catalina.Manager;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import br.com.senac.webpage.dao.UsuarioDAO;
import br.com.senac.webpage.model.Usuario;
import br.com.senac.webpage.model.UsuarioAllDto;
import br.com.senac.webpage.model.UsuarioDto;
import br.com.senac.webpage.util.IdSession;

@Controller
@RequestMapping("/paginaOrganizacao")
public class PaginaOrganizacaoController {

    @GetMapping
    public ModelAndView init(Model model) throws SQLException {
        
    	if(IdSession.idMain ==null || !IdSession.idType.equals("Administrador")) {
        	ModelAndView modelAndView = new ModelAndView("redirect:login");
        	System.out.println("login");
        	return modelAndView;
        	}else{
        		ModelAndView mv = new ModelAndView();
        		   
            	UsuarioDAO userRepository = new UsuarioDAO();
                List<UsuarioAllDto> users = userRepository.findAll(); // Substitua por sua lógica de acesso ao banco de dados
                System.out.println(users.get(0).getNome());
                mv.addObject("users", users);
         
                return mv;
            	}
        }
    
    @PostMapping
    public ModelAndView init(@ModelAttribute Usuario usuario) throws SQLException {
      
        ModelAndView mv = new ModelAndView();
        		   
            UsuarioDAO userRepository = new UsuarioDAO();
           userRepository.inserir(usuario); // Substitua por sua lógica de acesso ao banco de dados

           List<UsuarioAllDto> users = userRepository.findAll(); // Substitua por sua lógica de acesso ao banco de dados
           System.out.println(users.get(0).getNome());
           mv.addObject("users", users);
         
        return mv;
            
        }
}