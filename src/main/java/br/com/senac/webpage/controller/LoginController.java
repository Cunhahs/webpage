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
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.expression.Ids;

import br.com.senac.webpage.dao.UsuarioDAO;
import br.com.senac.webpage.model.UsuarioDto;
import br.com.senac.webpage.util.Cripto;
import br.com.senac.webpage.util.IdSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping    
    public String init(final Model model) {  	
    	model.addAttribute("usuarioDto", new UsuarioDto());
    	System.out.println("login");
    	
    	return "paginaUsuarioBackoffice";
    }

    public ModelAndView redirect(final Model model) {  	

    	ModelAndView modelAndView = new ModelAndView("redirect:paginaEscolherLista");
    	return modelAndView;
    }
    
    @PostMapping
    public ModelAndView result(@ModelAttribute UsuarioDto usuarioDto) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	System.out.println(usuarioDto.getEmail());
    	System.out.println(usuarioDto.getSenha());
    	Cripto cripto = new Cripto();
    	String senhaCriptografada = cripto.crip(usuarioDto.getSenha());
    	System.out.println(senhaCriptografada);
    	System.out.println(usuarioDto.getRequest());
    	
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
    	try {
			boolean valido = usuarioDAO.validarAdmin(usuarioDto.getEmail(), senhaCriptografada, "Administrador");
			if (valido == true) {
				ModelAndView modelAndView = new ModelAndView("redirect:paginaEscolherLista");
				IdSession.idMain = usuarioDAO.getId(usuarioDto.getEmail(), senhaCriptografada);
				IdSession.idType = "Administrador";
		    	return modelAndView;		
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}ModelAndView modelAndView = new ModelAndView("redirect:login");
    	System.out.println("init");
    	return modelAndView;	
    } 
}