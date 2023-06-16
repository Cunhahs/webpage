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

import br.com.senac.webpage.dao.UsuarioDAO;
import br.com.senac.webpage.model.UsuarioDto;
import br.com.senac.webpage.util.Cripto;
import br.com.senac.webpage.util.IdSession;

@Controller
@RequestMapping("/loginCliente")
public class LoginClienteController {

    @GetMapping
    public String init(final Model model) {  	
    	model.addAttribute("usuarioDto", new UsuarioDto());
    	System.out.println("init");
    	return "paginaLoginCliente";
    }
    
    @GetMapping("/carrinho")
    public String initCarrinho(final Model model) {  	
    	model.addAttribute("usuarioDto", new UsuarioDto());
    	System.out.println("init");
    	return "paginaLoginClienteTemp";
    }
    
    public String cadastrar(final Model model) {  	
    	System.out.println("init 2");
    	return "paginaCadastroCliente";
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
			boolean valido = usuarioDAO.validarCliente(usuarioDto.getEmail(), senhaCriptografada, "cliente");
			if (valido == true) {
				
		    	ModelAndView modelAndView = new ModelAndView("redirect:landingPageLogado");
		    	
		    	IdSession.idMain = usuarioDAO.getId(usuarioDto.getEmail(), senhaCriptografada);
		    	IdSession.idType = "cliente";
		    	System.out.println("Alterando sessão");
		    	System.out.println(IdSession.idMain);
		    	
		    	return modelAndView;		
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        	ModelAndView modelAndView = new ModelAndView("redirect:loginCliente");
        	return modelAndView;	
		
    }
    
    @PostMapping("/loginClienteTemp")
    public ModelAndView resultTemp(@ModelAttribute UsuarioDto usuarioDto) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	System.out.println(usuarioDto.getEmail());
    	System.out.println(usuarioDto.getSenha());
    	Cripto cripto = new Cripto();
    	String senhaCriptografada = cripto.crip(usuarioDto.getSenha());
    	System.out.println(senhaCriptografada);
    	
    	System.out.println(usuarioDto.getRequest());
    	
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
    	try {
			boolean valido = usuarioDAO.validarCliente(usuarioDto.getEmail(), senhaCriptografada, "cliente");
			if (valido == true) {
				
		    	ModelAndView modelAndView = new ModelAndView("redirect:/paginaCheckout");
		    	
		    	IdSession.idMain = usuarioDAO.getId(usuarioDto.getEmail(), senhaCriptografada);
		    	IdSession.idType = "cliente";
		    	System.out.println("Alterando sessão");
		    	System.out.println(IdSession.idMain);
		    	
		    	return modelAndView;		
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        	ModelAndView modelAndView = new ModelAndView("redirect:/loginCliente/carrinho");
        	return modelAndView;	
		
    }
}